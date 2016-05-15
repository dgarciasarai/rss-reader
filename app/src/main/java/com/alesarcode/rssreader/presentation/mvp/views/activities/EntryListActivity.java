package com.alesarcode.rssreader.presentation.mvp.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.di.components.DaggerRSSComponent;
import com.alesarcode.rssreader.di.modules.ActivityModule;

import butterknife.ButterKnife;

/**
 * Main activity for the App. Shows the list of RSS feed entries.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        this.initializeInjector();
    }

    private void initializeInjector() {
        DaggerRSSComponent.builder()
                .applicationComponent(((RSSReaderApplication) getApplication())
                        .getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
    }
}
