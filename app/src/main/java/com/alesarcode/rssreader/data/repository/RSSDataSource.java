package com.alesarcode.rssreader.data.repository;

import com.alesarcode.rssreader.data.parser.FeedParser;
import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.domain.repository.RSSRepository;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.parsers.ParserConfigurationException;

import rx.Observable;
import rx.Subscriber;

/**
 * Fake repository created for debug purposes.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
@Singleton
public class RSSDataSource implements RSSRepository {

    private List<FeedItem> mList;
    private FeedParser mParser;

    @Inject
    public RSSDataSource(FeedParser parser) {
        mList = new ArrayList<>();
        this.mParser = parser;
    }

    @Override
    public Observable<Feed> getNewEntries() {
        return Observable.create(new Observable.OnSubscribe<Feed>(){
            @Override
            public void call(Subscriber<? super Feed> subscriber) {
                Feed feed = null;
                try {
                    feed = mParser.getFeed();
                } catch (ParserConfigurationException e) {
                    subscriber.onError(e);
                } catch (SAXException e) {
                    subscriber.onError(e);
                } catch (IOException e) {
                    subscriber.onError(e);
                }

                mList.addAll(feed.getEntriesList());

                subscriber.onNext(feed);
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

                for (FeedItem item : mList) {
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
