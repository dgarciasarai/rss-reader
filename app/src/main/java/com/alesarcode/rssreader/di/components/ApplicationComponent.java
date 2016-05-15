package com.alesarcode.rssreader.di.components;

import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.di.modules.ApplicationModule;
import com.alesarcode.rssreader.domain.repository.RSSRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

/**
 * This component has the same lifetime as the application.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    RSSReaderApplication app();
    RSSRepository feedRepository();

    @Named("executor_sch") Scheduler executorScheduler();
    @Named("ui_sch") Scheduler uiScheduler();
}
