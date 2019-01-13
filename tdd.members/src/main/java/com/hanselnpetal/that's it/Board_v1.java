package com.taskboard.domain.entity;


import com.taskboard.domain.common.Color;
import com.taskboard.domain.common.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

/**
 * A board of the application.
 *
 * @author Victor Valcu
 */
@Entity
@Table(name="BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name="USER_ID", nullable = false)
    @Getter @Setter
    private Long userId;

    @Column(name="TITLE", unique = true, nullable = true, length = 50)
    @Getter @Setter
    private String title;

    @Column(name = "COLOR", nullable = true, length = 15)
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Color color = Color.BELIZE;

    @Column(name = "PERMISSION", nullable = true, length = 10)
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Permission permission = Permission.PRIVATE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false)
    @Getter @Setter
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED", nullable = false)
    @Getter @Setter
    private Date updated;

    public Board() {
    }

    public Board(String title, Long userId) {
        Assert.hasLength(title, "Title must not be empty");
        Assert.notNull(userId, "UserId must not be null");
        this.userId = userId;
        this.title = title;
    }

    public static Board create(String title, Long userId, Permission permission) {
        Board board = new Board(title, userId);
        board.setPermission(permission);
        return board;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
