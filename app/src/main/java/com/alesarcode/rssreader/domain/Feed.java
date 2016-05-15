package com.alesarcode.rssreader.domain;

import java.util.List;

/**
 * Base model for Feed data.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class Feed {

    private List<FeedItem> mList;
    private String mTitle;

    public Feed(String title, List<FeedItem> list) {
        this.mList = list;
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<FeedItem> getEntriesList() {
        return mList;
    }
}
