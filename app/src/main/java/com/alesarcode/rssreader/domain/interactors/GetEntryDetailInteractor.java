package com.alesarcode.rssreader.domain.interactors;

import com.alesarcode.rssreader.domain.repository.RSSRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * This class represents an {@link Interactor} for retrieving data
 * related to an specific {@link com.alesarcode.rssreader.domain.FeedItem}
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class GetEntryDetailInteractor extends Interactor {

    private final RSSRepository mRepository;
    private final int mItemId;

    @Inject
    public GetEntryDetailInteractor(int itemId, RSSRepository repository,
                                    @Named("executor_sch") Scheduler mExecutorScheduler,
                                    @Named("ui_sch") Scheduler mUiScheduler) {
        super(mExecutorScheduler, mUiScheduler);
        this.mItemId = itemId;
        this.mRepository = repository;
    }

    @Override
    protected Observable buildInteractorObservable() {
        return this.mRepository.getEntry(mItemId);
    }
}
