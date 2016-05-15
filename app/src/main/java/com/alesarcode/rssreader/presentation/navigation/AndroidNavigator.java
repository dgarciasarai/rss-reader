package com.alesarcode.rssreader.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.alesarcode.rssreader.presentation.activities.EntryDetailActivity;

import javax.inject.Inject;

/**
 * Navigator implementation for Android app.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class AndroidNavigator implements Navigator {

    @Inject
    public AndroidNavigator() {
    }

    @Override
    public void navigateToEntryDetail(Context context, int id) {
        Intent callingIntent = new Intent(context, EntryDetailActivity.class);
        callingIntent.putExtra(EntryDetailActivity.INTENT_EXTRA_ENTRY_ID, id);
        context.startActivity(callingIntent);
    }
}
