package com.alesarcode.rssreader.domain;

import rx.Subscriber;

/**
 * Base class for subscribers of this application. Implements all methods of a subscriber
 * to allow developers to only implements needed methods.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        //no-op by default
    }

    @Override
    public void onError(Throwable e) {
        //no-op by default
    }

    @Override
    public void onNext(T t) {
        //no-op by default
    }
}
