package com.alesarcode.rssreader.di.modules;

import com.alesarcode.rssreader.di.scopes.PerActivity;
import com.alesarcode.rssreader.domain.interactors.GetEntryDetailInteractor;
import com.alesarcode.rssreader.domain.interactors.GetNewEntriesInteractor;
import com.alesarcode.rssreader.domain.interactors.Interactor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

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
    Interactor provideGetEntryDetailInteractor(GetEntryDetailInteractor entryDetailInteractor) {
        return entryDetailInteractor;
    }
}
