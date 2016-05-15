package com.alesarcode.rssreader.di.modules;

import com.alesarcode.rssreader.di.scopes.PerActivity;
import com.alesarcode.rssreader.domain.interactors.GetEntryDetailInteractor;
import com.alesarcode.rssreader.domain.interactors.GetNewEntriesInteractor;
import com.alesarcode.rssreader.domain.interactors.Interactor;
import com.alesarcode.rssreader.domain.repository.RSSRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Dagger module that provides feed related collaborators.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Module
public class RSSModule {

    private int mItemId = -1;

    public RSSModule() {}

    public RSSModule(int itemId) {
        this.mItemId = itemId;
    }

    @Provides @PerActivity @Named("feedList")
    Interactor provideGetNewEntriesInteractor(GetNewEntriesInteractor entriesInteractor) {
        return entriesInteractor;
    }

    @Provides @PerActivity @Named("feedDetail")
    Interactor provideGetEntryDetailInteractor(RSSRepository repository,
                                               @Named("executor_sch") Scheduler mExecutorScheduler,
                                               @Named("ui_sch") Scheduler mUiScheduler) {
        return new GetEntryDetailInteractor(mItemId, repository, mExecutorScheduler, mUiScheduler);
    }
}
