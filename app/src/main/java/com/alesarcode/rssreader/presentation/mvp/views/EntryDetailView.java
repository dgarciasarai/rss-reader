package com.alesarcode.rssreader.presentation.mvp.views;

import com.alesarcode.rssreader.presentation.model.EntryModel;

/**
 * Interface used as view representing a single {@link EntryModel}.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface EntryDetailView extends LoadView {

    /**
     * Render an entry in the UI.
     *
     * @param entry The {@link EntryModel} that will be shown.
     */
    void renderItem(EntryModel entry);
}
