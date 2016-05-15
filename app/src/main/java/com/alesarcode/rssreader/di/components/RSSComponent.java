package com.alesarcode.rssreader.di.components;

import com.alesarcode.rssreader.di.modules.ActivityModule;
import com.alesarcode.rssreader.di.modules.RSSModule;
import com.alesarcode.rssreader.di.scopes.PerActivity;
import com.alesarcode.rssreader.presentation.activities.EntryDetailActivity;
import com.alesarcode.rssreader.presentation.activities.EntryListActivity;

import dagger.Component;

/**
 * Inject feed specific activities.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, RSSModule.class})
public interface RSSComponent {

    void inject(EntryListActivity activity);
    void inject(EntryDetailActivity entryDetailActivity);
}
