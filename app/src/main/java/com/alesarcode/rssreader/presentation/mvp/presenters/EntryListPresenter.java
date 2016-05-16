package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.di.scopes.PerActivity;
import com.alesarcode.rssreader.domain.DefaultSubscriber;
import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.domain.exceptions.DefaultErrorBundle;
import com.alesarcode.rssreader.domain.interactors.Interactor;
import com.alesarcode.rssreader.presentation.converters.DomainToPresentationMapper;
import com.alesarcode.rssreader.presentation.model.Model;
import com.alesarcode.rssreader.presentation.mvp.views.EntryListView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * This {@link Presenter} links feed items list view with the model layer.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@PerActivity
public class EntryListPresenter extends BasePresenter<EntryListView> {

    private final Interactor mInteractor;

    @Inject
    public EntryListPresenter(@Named("feedList") Interactor interactor) {
        this.mInteractor = interactor;
    }

    @Override
    public void initialize() {
        this.mView.showLoading();
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
        List<FeedItem> feedItems = feed.getEntriesList();

        Collections.sort(feedItems, new Comparator<FeedItem>() {
            @Override
            public int compare(FeedItem lhs, FeedItem rhs) {
                Date lhsDate = lhs.getDate();
                Date rhsDate = rhs.getDate();

                if (lhsDate == null && rhsDate == null) {
                    return 0;
                } else if (lhsDate == null) {
                    return -1;
                } else if (rhsDate == null) {
                    return 1;
                }

                return lhsDate.compareTo(rhsDate);
            }
        });

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
