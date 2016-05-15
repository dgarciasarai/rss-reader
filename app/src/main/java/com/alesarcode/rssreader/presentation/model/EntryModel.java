package com.alesarcode.rssreader.presentation.model;

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

    public EntryModel(int id) {
        this.mId = id;
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

    public void setImageUrl(String image) {
        this.mImageUrl = image;
    }
}
