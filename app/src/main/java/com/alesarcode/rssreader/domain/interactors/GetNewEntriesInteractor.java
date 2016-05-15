package com.alesarcode.rssreader.domain.interactors;

import com.alesarcode.rssreader.domain.repository.RSSRepository;

import java.net.URL;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Interactor for retrieving new entries from repository.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class GetNewEntriesInteractor extends Interactor {

    private RSSRepository mRepository;

    @Inject
    public GetNewEntriesInteractor(RSSRepository repository,
                                   @Named("executor_sch") Scheduler mExecutorScheduler,
                                   @Named("ui_sch") Scheduler mUiScheduler) {
        super(mExecutorScheduler, mUiScheduler);
        this.mRepository = repository;
    }

    @Override
    protected Observable buildInteractorObservable() {
        return this.mRepository.getNewEntries();
    }
}
