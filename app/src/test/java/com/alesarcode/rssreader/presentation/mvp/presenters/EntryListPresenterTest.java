package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.domain.interactors.GetNewEntriesInteractor;
import com.alesarcode.rssreader.presentation.mvp.views.EntryListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Presenter for EntryListPresenter.
 *
 * @author Sarai Díaz García
 * @version %I%
 * @see EntryListPresenter
 */
public class EntryListPresenterTest {

    private EntryListPresenter mPresenter;

    @Mock
    private GetNewEntriesInteractor mInteractor;

    @Mock
    private EntryListView mView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new EntryListPresenter(mInteractor);
        mPresenter.setView(mView);
    }
    
    @Test
    public void testShouldShowLoadingIndicatorOnInitialize() {
        mPresenter.initialize();
        shouldShowLoading();
        shouldHideRetry();
    }

    @Test
    public void testShouldRequestAllRSSEntriesOnInitalize() {
        mPresenter.initialize();
        shouldRequestEntriesFromInteractor();
    }

    @Test
    public void testShouldDestroyAllOnDestroyView(){
        this.mPresenter.onViewDestroy();
        shouldUnsubscribeInteractor();
    }

    private void shouldHideRetry() {
        verify(mView).hideRetry();
    }

    private void shouldRequestEntriesFromInteractor() {
        verify(mInteractor).execute(any(Subscriber.class));
    }

    private void shouldShowLoading() {
        verify(mView).showLoading();
    }

    private void shouldUnsubscribeInteractor() {
        verify(mInteractor).unsubscribe();
    }
}
