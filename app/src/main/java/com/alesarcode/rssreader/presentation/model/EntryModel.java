package com.alesarcode.rssreader.presentation.model;

import java.util.Date;

/**
 * Class that represents a Entry in presentation layer.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryModel extends Model {

    private final int mId;
    private String mTitle;
    private String mImageUrl;
    private String mDescription;
    private String mLink;

    private Date mDate;

    public EntryModel(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public void setImageUrl(String image) {
        this.mImageUrl = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public Date getDate() {
        return mDate;
    }
}
