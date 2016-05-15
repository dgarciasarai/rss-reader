package com.alesarcode.rssreader.domain.repository;

import com.alesarcode.rssreader.domain.Feed;

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
     */
    Observable<Feed> getNewEntries();
}
