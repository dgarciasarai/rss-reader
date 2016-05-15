package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.domain.interactors.GetEntryDetailInteractor;
import com.alesarcode.rssreader.domain.interactors.GetNewEntriesInteractor;
import com.alesarcode.rssreader.presentation.mvp.views.EntryDetailView;
import com.alesarcode.rssreader.presentation.mvp.views.EntryListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Presenter for EntryDetailsPresenter.
 *
 * @author Sarai Díaz García
 * @version %I%
 * @see EntryDetailsPresenter
 */
public class EntryDetailsPresenterTest {

    private EntryDetailsPresenter mPresenter;

    @Mock
    private GetEntryDetailInteractor mInteractor;

    @Mock
    private EntryDetailView mView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new EntryDetailsPresenter(mInteractor);
        mPresenter.setView(mView);
    }

    @Test
    public void testShouldShowLoadingIndicatorOnInitialize() {
        mPresenter.initialize();
        shouldShowLoading();
        shouldHideRetry();
    }

    @Test
    public void testShouldRequestEntryOnInitalize() {
        mPresenter.initialize();
        shouldRequestEntryFromInteractor();
    }

    @Test
    public void testShouldDestroyAllOnDestroyView(){
        this.mPresenter.onViewDestroy();
        shouldUnsubscribeInteractor();
    }

    private void shouldHideRetry() {
        verify(mView).hideRetry();
    }

    private void shouldRequestEntryFromInteractor() {
        verify(mInteractor).execute(any(Subscriber.class));
    }

    private void shouldShowLoading() {
        verify(mView).showLoading();
    }

    private void shouldUnsubscribeInteractor() {
        verify(mInteractor).unsubscribe();
    }
}
