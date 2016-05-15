package com.alesarcode.rssreader.data.repository;

import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.domain.repository.RSSRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Fake repository created for debug purposes.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Singleton
public class FakeDataSource implements RSSRepository {

    private List<FeedItem> demoList = Arrays.asList(
            new FeedItem(1),
            new FeedItem(2),
            new FeedItem(3),
            new FeedItem(4),
            new FeedItem(5),
            new FeedItem(6),
            new FeedItem(7),
            new FeedItem(8),
            new FeedItem(9),
            new FeedItem(10),
            new FeedItem(11),
            new FeedItem(12)
    );

    @Inject
    public FakeDataSource() {
    }

    @Override
    public Observable<Feed> getNewEntries() {
        return Observable.create(new Observable.OnSubscribe<Feed>(){
            @Override
            public void call(Subscriber<? super Feed> subscriber) {
                subscriber.onNext(new Feed("Demo Feed", demoList));
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<FeedItem> getEntry(final int itemId) {
        return Observable.create(new Observable.OnSubscribe<FeedItem>(){
            @Override
            public void call(Subscriber<? super FeedItem> subscriber) {
                FeedItem foundItem = null;

                for (FeedItem item : demoList) {
                    if (item.getId() == itemId) {
                        foundItem = item;
                        break;
                    }
                }

                if (foundItem == null) {
                    subscriber.onError(new Exception("Item not found"));
                } else {
                    subscriber.onNext(foundItem);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
