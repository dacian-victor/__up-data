package com.rss.feeder.feeder.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rss.feeder.feeder.domain.FeedItem;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedItemDTO {
    private String description;
    private String author;
    private String title;
    private String articleId;

    public FeedItemDTO(FeedItem item) {
        this.description = item.getDescription();
        this.author = item.getAuthor();
        this.title = item.getTitle();
        this.articleId = item.getArticleId();
    }
}
