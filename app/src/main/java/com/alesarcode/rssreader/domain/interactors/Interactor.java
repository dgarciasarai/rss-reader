package com.alesarcode.rssreader.domain.interactors;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.TestSubscriber;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for an interactor.
 * Any interactor should implement this contract.
 *
 * Interactors should return the result using {@link rx.Subscriber}
 * that will execute it in a background thread and send the result to
 * the main thread.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public abstract class Interactor {

    private final Scheduler mExecutorScheduler;
    private final Scheduler mUiScheduler;

    private Subscription subscription = Subscriptions.empty();

    public Interactor(Scheduler mExecutorScheduler, Scheduler mUiScheduler) {
        this.mExecutorScheduler = mExecutorScheduler;
        this.mUiScheduler = mUiScheduler;
    }

    /**
     * Executes the {@link Interactor}.
     *
     * @param subscriber the object listen to the observable returned by
     *                   {@link #buildInteractorObservable()}.
     */
    public void execute(Subscriber subscriber) {
        this.subscription = this.buildInteractorObservable()
                .subscribeOn(mExecutorScheduler)
                .observeOn(mUiScheduler)
                .subscribe(subscriber);

    }

    /**
     * Unsubscribe current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * Builds the {@link rx.Observable} used when this {@link Interactor}
     * is used.
     *
     * @return the observable object.
     */
    protected abstract Observable buildInteractorObservable();
}
