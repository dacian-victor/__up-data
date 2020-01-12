package com.rss.feeder.feeder.domain;

import com.rss.feeder.feeder.service.FeedItemDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class FeedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(length=1500)
    private String description;
    private String author;
    private String articleId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] articlePicture;

    private Date pubDate;

    /**
     * Required by Hibernate.
     */
    public FeedItem() {}

    public FeedItem(String articleId, String title, String description, String author) {
        this.description = description;
        this.author = author;
        this.articleId = articleId;
        this.title = title;
    }

    public FeedItem(FeedItemDTO dto) {
        this.description = dto.getDescription();
        this.author = dto.getAuthor();
        this.articleId = dto.getArticleId();
    }

    public FeedItem(String message) {
        this.description = message;
        this.author = message;
        this.articleId = message;
        this.title = message;
    }
}
