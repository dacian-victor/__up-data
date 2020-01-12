package com.rss.feeder.feeder.common;

import com.rss.feeder.feeder.domain.FeedItem;
import com.rss.feeder.feeder.service.FeedItemDTO;

public class FeedMapper {
    public static FeedItemDTO toDTO(FeedItem item) {
        return new FeedItemDTO(item);
    }

    public static FeedItem fromDTO(FeedItemDTO dto) {
        return new FeedItem(dto);
    }
}
