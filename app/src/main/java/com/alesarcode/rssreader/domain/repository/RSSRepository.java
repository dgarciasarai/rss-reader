package com.alesarcode.rssreader.domain.repository;

import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;

import java.net.URL;

import rx.Observable;

/**
 * Repository for RSS Feeds.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface RSSRepository {

    /**
     * Get entries from stored URL.
     * @return Observable with the feed wrapper.
     */
    Observable<Feed> getNewEntries();

    /**
     * Get entry with a given id.
     * @param itemId given entry id.
     * @return Observable with the feed item.
     */
    Observable<FeedItem> getEntry(int itemId);
}
