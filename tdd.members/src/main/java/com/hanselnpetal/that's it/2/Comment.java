package com.one2one.one2one.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "BODY")
    private String body;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    public Comment(String author, String body) {
        this.author = author;
        this.body = body;
    }
}
