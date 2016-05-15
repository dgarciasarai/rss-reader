package com.alesarcode.rssreader;

import android.app.Application;

import com.alesarcode.rssreader.di.components.ApplicationComponent;
import com.alesarcode.rssreader.di.components.DaggerApplicationComponent;
import com.alesarcode.rssreader.di.modules.ApplicationModule;

/**
 * @author Sarai Díaz García
 * @version %I%
 */
public class RSSReaderApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
