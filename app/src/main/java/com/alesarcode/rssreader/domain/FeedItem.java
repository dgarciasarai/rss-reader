package com.alesarcode.rssreader.domain;

/**
 * Base model for Feed items.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class FeedItem {

    private int mId;

    public FeedItem(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }
}
