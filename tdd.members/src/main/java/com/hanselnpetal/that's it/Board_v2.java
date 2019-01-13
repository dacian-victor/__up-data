package com.taskboard.domain.entity;


import com.sun.istack.internal.NotNull;
import com.taskboard.domain.common.Color;
import com.taskboard.domain.common.Permission;
import lombok.*;
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
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name="USER_ID", nullable = false)
    private Long userId;

    @NotNull
    @Column(name="TITLE", unique = true, nullable = false)
    private String title;

    @NotNull
    @Column(name = "COLOR", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Color color = Color.BELIZE;

    @NotNull
    @Column(name = "PERMISSION", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Permission permission = Permission.PRIVATE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED", nullable = false)
    private Date updated;


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
