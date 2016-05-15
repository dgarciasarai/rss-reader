package com.alesarcode.rssreader.domain.interactors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for base Interactor.
 *
 * @author Sarai Díaz García
 * @version %I%
 * @see Interactor
 */
public class InteractorTest {

    private Scheduler testExecutorScheduler;
    private Scheduler testUiScheduler;

    private InteractorTestClass mInteractor;

    @Before
    public void setUp() {
        testExecutorScheduler = new TestScheduler();
        testUiScheduler = new TestScheduler();
        this.mInteractor = new InteractorTestClass(testExecutorScheduler, testUiScheduler);
    }

    @Test
    public void testShouldReturnCorrectResultOnBuildInteractorObservable() {
        TestSubscriber<Integer> subscriber = givenATestSubscriber();
        this.mInteractor.execute(subscriber);
        assertThat(subscriber.getOnNextEvents().size(), is(0));
    }

    @Test
    public void testShouldUnsubscribeOnUnsubscribe() {
        TestSubscriber<Integer> subscriber = givenATestSubscriber();
        this.mInteractor.execute(subscriber);
        this.mInteractor.unsubscribe();
        assertThat(subscriber.isUnsubscribed(), is(true));
    }

    private TestSubscriber<Integer> givenATestSubscriber() {
        return new TestSubscriber<>();
    }

    private class InteractorTestClass extends Interactor {
        public InteractorTestClass(Scheduler mExecutorScheduler, Scheduler mUiScheduler) {
            super(mExecutorScheduler, mUiScheduler);
        }

        @Override
        protected Observable buildInteractorObservable() {
            return Observable.empty();
        }
    }
}
