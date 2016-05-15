package com.alesarcode.rssreader.di.modules;

import android.app.Activity;
import android.content.Context;

import com.alesarcode.rssreader.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context context) {
        this.mContext = context;
    }

    @Provides @PerActivity
    Context provideActivityContext() {
        return mContext;
    }
}
