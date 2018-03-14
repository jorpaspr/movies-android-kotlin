# Movies

Movies is a sample Android app that shows movies information from the [Open Movie Database API](http://www.omdbapi.com/).

It is 100% Kotlin codebase and implements MVP pattern using RxJava, Retrofit and Dagger as well as Room from Android Architecture Components for the local cache and JUnit and Mockito to test the presenters.

The app performs a first call to search on the Open Movie Database and then makes a new call for each movie to retrieve the missing information. The search parameters are hardcoded for this sample (movies from 2016 containing the term "star").

Results are stored on a local database so it works offline.

The user can tap on a movie to view more details and bookmark it. Bookmarked movies appear at the top of the list with a bookmark indicator.

## Technologies

* MVP pattern is implemented.

* Room from Android Architecture Components is used for the local cache. A flowable is used as the source of data, so changes on the database update the list.

* RxJava is used as well through the application.

* Retrofit 2 for accessing the Open Movie Database API. The API key must be configured on its `build.gradle` build config field. You can get a free one on http://www.omdbapi.com/apikey.aspx

* Dagger 2 as the dependency injection framework.

* JUnit and Mockito are used to test the presenters.
