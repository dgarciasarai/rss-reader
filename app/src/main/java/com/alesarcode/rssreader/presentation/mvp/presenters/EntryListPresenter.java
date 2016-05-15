package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.domain.DefaultSubscriber;
import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.domain.exceptions.DefaultErrorBundle;
import com.alesarcode.rssreader.domain.interactors.GetNewEntriesInteractor;
import com.alesarcode.rssreader.presentation.converters.DomainToPresentationMapper;
import com.alesarcode.rssreader.presentation.model.Model;
import com.alesarcode.rssreader.presentation.mvp.views.EntryListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * This {@link Presenter} links feed items list view with the model layer.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryListPresenter extends BasePresenter<EntryListView> {

    private final GetNewEntriesInteractor mInteractor;

    @Inject
    public EntryListPresenter(GetNewEntriesInteractor interactor) {
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
    private Subscriber<Feed> getSubscriber() {
        return new DefaultSubscriber<Feed>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
                mView.showRetry();
                EntryListPresenter.this.showError(new DefaultErrorBundle((Exception) e));
            }

            @Override
            public void onNext(Feed feed) {
                EntryListPresenter.this.showFeed(feed);
            }
        };
    }

    /**
     * Tells the view to add items to the list.
     *
     * @param feed wrapper of {@link com.alesarcode.rssreader.domain.FeedItem}.
     */
    private void showFeed(Feed feed) {
        final List<FeedItem> feedItems = feed.getEntriesList();

        this.mView.showFeedTitle(feed.getTitle());

        if (!feedItems.isEmpty()) {
            final Collection<Model> entriesCollection =
                    DomainToPresentationMapper.convertToModelList(feedItems);
            this.mView.add(entriesCollection);
        } else {
            this.mView.showError(context().getString(R.string.entries_not_found));
        }
    }
}
