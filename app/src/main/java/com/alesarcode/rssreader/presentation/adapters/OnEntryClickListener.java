package com.alesarcode.rssreader.presentation.adapters;

import com.alesarcode.rssreader.presentation.model.EntryModel;

/**
 * Interface for controlling clicks in feed entry list.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface OnEntryClickListener {

    /**
     * Triggers the entry clicked event.
     * @param entry Clicked {@link EntryModel}.
     */
    void onEntryClicked(EntryModel entry);
}
