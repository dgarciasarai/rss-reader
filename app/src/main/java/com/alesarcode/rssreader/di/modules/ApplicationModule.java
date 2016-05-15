package com.alesarcode.rssreader.di.modules;

import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.data.repository.FakeDataSource;
import com.alesarcode.rssreader.domain.repository.RSSRepository;

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
    RSSRepository providesFeedRepository(FakeDataSource fakeDataSource) {
        return fakeDataSource;
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
