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
            new FeedItem(1, "Hablan las esclavas sexuales que sobrevivieron al Estado Islámico", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633280461034.jpg"),
            new FeedItem(2, "Seseña dio el vertedero a una empresa senegalesa creada para delinquir", null),
            new FeedItem(3, "Un notario tiene un acta con la oferta del entorno de la Infanta a Manos Limpias", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633281072561.jpg"),
            new FeedItem(4, "El 'niño' Verstappen se doctora el día que Hamilton arrolla a Rosberg", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633306266318.jpg"),
            new FeedItem(5, "Los \"indignados\" vuelven a las calles en el quinto aniversario del 15-M", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633328614577.jpg"),
            new FeedItem(6, "Íñigo Errejón: \"Entre Podemos y Marine Le Pen hay un hilo común\"", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633281317990.jpg"),
            new FeedItem(7, "Jamala, la ganadora de Eurovisión que lanzó un mensaje contra Stalin", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633211895055.jpg"),
            new FeedItem(8, "Núria Parlon y Patxi López, entre los 'ministrables' de Sánchez", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633343454069.jpg"),
            new FeedItem(9, "La arriesgada vida sexual del candidato Donald Trump", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633212984933.jpg"),
            new FeedItem(10, "Apoyo en la calle a Dilma Rousseff: \"Queremos que vuelva\"", null),
            new FeedItem(11, "Así son las cajetillas de la ofensiva antihumos", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633187652566.jpg"),
            new FeedItem(12, "Onán vive en el Festival de cine de Cannes", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633295660264.jpg")
    );

    @Inject
    public FakeDataSource() {
    }

    @Override
    public Observable<Feed> getNewEntries() {
        return Observable.create(new Observable.OnSubscribe<Feed>(){
            @Override
            public void call(Subscriber<? super Feed> subscriber) {
                subscriber.onNext(new Feed("Portada // elmundo", demoList));
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
