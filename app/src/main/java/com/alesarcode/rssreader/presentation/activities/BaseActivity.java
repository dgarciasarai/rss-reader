package com.alesarcode.rssreader.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.di.components.ApplicationComponent;
import com.alesarcode.rssreader.di.components.DaggerRSSComponent;
import com.alesarcode.rssreader.di.components.RSSComponent;
import com.alesarcode.rssreader.di.modules.ActivityModule;
import com.alesarcode.rssreader.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * @author Sarai Díaz García
 * @version %I%
 */
public class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Gets the main application component for DI.
     * @return Main {@link ApplicationComponent}.
     */
    private ApplicationComponent getApplicationComponent() {
        return ((RSSReaderApplication) getApplication()).getApplicationComponent();
    }
}
