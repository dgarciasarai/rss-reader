package com.alesarcode.rssreader.presentation.mvp.views;

import com.alesarcode.rssreader.presentation.model.EntryModel;

/**
 * Interface used as view representing a list of {@link EntryModel}.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface EntryListView extends ListDataView {

    /**
     * Show feed title in the view.
     *
     * @param title String title.
     */
    void showFeedTitle(String title);
}
