package com.alesarcode.rssreader.di.components;

import android.content.Context;

import com.alesarcode.rssreader.di.modules.ActivityModule;
import com.alesarcode.rssreader.di.scopes.PerActivity;
import com.alesarcode.rssreader.presentation.mvp.views.activities.EntryListActivity;

import dagger.Component;

/**
 * Component for all activities.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * Get activity context.
     *
     * @return Context
     */
    Context context();
}
