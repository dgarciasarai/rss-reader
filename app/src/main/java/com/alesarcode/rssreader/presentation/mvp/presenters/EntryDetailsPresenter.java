package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.domain.DefaultSubscriber;
import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.domain.exceptions.DefaultErrorBundle;
import com.alesarcode.rssreader.domain.interactors.GetEntryDetailInteractor;
import com.alesarcode.rssreader.presentation.converters.DomainToPresentationMapper;
import com.alesarcode.rssreader.presentation.model.Model;
import com.alesarcode.rssreader.presentation.mvp.views.EntryDetailView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * This {@link Presenter} links one feed item view with the model layer.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryDetailsPresenter extends BasePresenter<EntryDetailView> {

    private final GetEntryDetailInteractor mInteractor;

    @Inject
    public EntryDetailsPresenter(GetEntryDetailInteractor interactor) {

        this.mInteractor = interactor;
    }

    @Override
    public void initialize() {
        this.mView.showLoading();
        this.mView.hideRetry();
        this.mInteractor.execute(getSubscriber());
    }

    @Override
    public void onViewDestroy() {
        this.mInteractor.unsubscribe();
        this.setView(null);
    }

    /**
     * Returns the related subscriber for this presenter.
     *
     * @return {@link Subscriber} object.
     */
    private Subscriber<FeedItem> getSubscriber() {
        return new DefaultSubscriber<FeedItem>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
                mView.showRetry();
                EntryDetailsPresenter.this.showError(new DefaultErrorBundle((Exception) e));
            }

            @Override
            public void onNext(FeedItem feedItem) {
                EntryDetailsPresenter.this.showFeedItem(feedItem);
            }
        };
    }

    private void showFeedItem(FeedItem feedItem) {
        this.mView.renderItem(DomainToPresentationMapper.convert(feedItem));
    }
}
