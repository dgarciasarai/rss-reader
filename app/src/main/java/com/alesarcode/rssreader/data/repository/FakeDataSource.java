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

    private String mDescription1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private String mDescription2 = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
    private String mDescription3 = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?";

    private List<FeedItem> demoList = Arrays.asList(
            new FeedItem(1, "Hablan las esclavas sexuales que sobrevivieron al Estado Islámico", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633280461034.jpg", mDescription1),
            new FeedItem(2, "Seseña dio el vertedero a una empresa senegalesa creada para delinquir", null, mDescription2),
            new FeedItem(3, "Un notario tiene un acta con la oferta del entorno de la Infanta a Manos Limpias", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633281072561.jpg", mDescription1),
            new FeedItem(4, "El 'niño' Verstappen se doctora el día que Hamilton arrolla a Rosberg", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633306266318.jpg", mDescription1),
            new FeedItem(5, "Los \"indignados\" vuelven a las calles en el quinto aniversario del 15-M", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633328614577.jpg", mDescription2),
            new FeedItem(6, "Íñigo Errejón: \"Entre Podemos y Marine Le Pen hay un hilo común\"", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633281317990.jpg", mDescription3),
            new FeedItem(7, "Jamala, la ganadora de Eurovisión que lanzó un mensaje contra Stalin", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633211895055.jpg", mDescription1),
            new FeedItem(8, "Núria Parlon y Patxi López, entre los 'ministrables' de Sánchez", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633343454069.jpg", mDescription2),
            new FeedItem(9, "La arriesgada vida sexual del candidato Donald Trump", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633212984933.jpg", mDescription3),
            new FeedItem(10, "Apoyo en la calle a Dilma Rousseff: \"Queremos que vuelva\"", null, mDescription1),
            new FeedItem(11, "Así son las cajetillas de la ofensiva antihumos", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633187652566.jpg", mDescription1),
            new FeedItem(12, "Onán vive en el Festival de cine de Cannes", "http://estaticos.elmundo.es/assets/multimedia/imagenes/2016/05/15/14633295660264.jpg", mDescription2)
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
