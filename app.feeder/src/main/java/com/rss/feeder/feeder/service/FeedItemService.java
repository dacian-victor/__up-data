package com.rss.feeder.feeder.service;

import com.rss.feeder.feeder.common.FeedMapper;
import com.rss.feeder.feeder.domain.FeedItem;
import com.rss.feeder.feeder.domain.FeedItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FeedItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedItemService.class);

    private FeedItemRepository repository;

    public FeedItemService(FeedItemRepository repository) {
        this.repository = repository;
    }

    public List<FeedItemDTO> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<FeedItem> todoEntries = StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        LOGGER.info("Found {} todo entries", todoEntries.size());

        return todoEntries.stream().map(t-> FeedMapper.toDTO(t)).collect(Collectors.toList());
    }
}
