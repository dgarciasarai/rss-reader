package com.alesarcode.rssreader.presentation.navigation;

import android.content.Context;

import com.alesarcode.rssreader.presentation.model.EntryModel;

/**
 * Default Navigator for the App.
 * All navigators should implement this interface.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface Navigator {

    /**
     * Navigate to entry detail view with given entry id.
     *
     * @param context Actual context.
     * @param id {@link EntryModel} id.
     */
    void navigateToEntryDetail(Context context, int id);
}
