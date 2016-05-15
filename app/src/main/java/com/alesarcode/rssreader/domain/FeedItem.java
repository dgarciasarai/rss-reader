package com.alesarcode.rssreader.domain;

/**
 * Base model for Feed items.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class FeedItem {

    private int mId;
    private String mTitle;
    private String mImageUrl;

    public FeedItem(int id, String title, String imageUrl) {
        this.mId = id;
        mTitle = title;
        mImageUrl = imageUrl;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
