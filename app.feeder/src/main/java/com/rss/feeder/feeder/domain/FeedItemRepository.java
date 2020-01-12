package com.rss.feeder.feeder.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedItemRepository extends CrudRepository<FeedItem, Long> {

}
