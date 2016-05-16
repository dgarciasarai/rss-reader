package com.alesarcode.rssreader.domain;

import java.util.ArrayList;
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

    public Feed() {
    }

    public String getTitle() {
        return mTitle;
    }

    public List<FeedItem> getEntriesList() {
        return mList;
    }

    public void setItems(List<FeedItem> list) {
        this.mList = list;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void addItem(FeedItem item) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.add(item);
    }
}
