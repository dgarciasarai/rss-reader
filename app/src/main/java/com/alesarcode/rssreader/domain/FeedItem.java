package com.alesarcode.rssreader.domain;

import java.util.Date;

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
    private String mDescription;
    private String mLink;
    private Date mDate;

    public FeedItem() {
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

    public String getDescription() {
        return mDescription;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getLink() {
        return mLink;
    }

    public Date getDate() {
        return mDate;
    }
}
