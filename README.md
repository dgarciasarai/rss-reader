# RSSReader App
RSS Reader App developed using **MVP**, **Dagger2**, **RxJava**, **Butterknife**, **Glide** and others.

The list of used libraries are:
* Support widgets such as RecyclerView or CardView.
* RxJava and RxAndroid for interactions between repository-interactor-presenter.
* Dagger2 for dependency injection.
* Butterknife for view binding.
* Glide for load images from URL.
* JUnit and Mockito for testin purposes.

This app has been developed using MVP architecture (Model-View-Presenter). For implementing this architecture the app has been divided into the following layers:
* **Domain**: contains the app interactors (use cases), repository interface and models.
* **Presenter**: contains the presenters and view interfaces.
* **Data**: contains the repository implementation and needed parsers for RSS, Atom, RDF.
* **Presentation**: contains the android app, including activities, dependency injection elements, presentation models and converters (from domain models to presentation ones).

TDD was used for the fist stages of the development: interactors and presenters.

![RecyclerView with feed items](/img/List.png)
![RecyclerView with feed items](/img/Detail.png)
