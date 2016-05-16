package com.alesarcode.rssreader.di.modules;

import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.data.FakeUserPreferences;
import com.alesarcode.rssreader.data.UserPreferences;
import com.alesarcode.rssreader.data.repository.RSSDataSource;
import com.alesarcode.rssreader.domain.repository.RSSRepository;
import com.alesarcode.rssreader.presentation.navigation.AndroidNavigator;
import com.alesarcode.rssreader.presentation.navigation.Navigator;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Dagger module that provides application related collaborators.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Module
public class ApplicationModule {

    private final RSSReaderApplication mApplication;

    public ApplicationModule(RSSReaderApplication application) {
        this.mApplication = application;
    }

    @Provides @Singleton
    RSSReaderApplication providesApplicationContext() {
        return mApplication;
    }

    @Provides @Singleton
    RSSRepository providesFeedRepository(RSSDataSource RSSDataSource) {
        return RSSDataSource;
    }

    @Provides @Singleton
    UserPreferences providesUserPreferences(FakeUserPreferences fakeUserPreferences) {
        return fakeUserPreferences;
    }

    @Provides @Singleton
    Navigator providesNavigator(AndroidNavigator navigator) {
        return navigator;
    }

    @Provides @Named("executor_sch")
    Scheduler provideExecutorScheduler() {
        return Schedulers.newThread();
    }

    @Provides @Named("ui_sch")
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
