package com.example.tmdb.Network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmdb.BuildConfig;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.Reviews;
import com.example.tmdb.model.Trailers;
import com.example.tmdb.model.responses.MovieListResponse;
import com.example.tmdb.model.responses.MovieReviewResponse;
import com.example.tmdb.model.responses.MovieTrailerResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RemoteNetworkCall {

    private static MutableLiveData<List<Movie>> data = new MutableLiveData<>();
    private static MutableLiveData<List<Reviews>> dataReviews = new MutableLiveData<>();
    private static MutableLiveData<List<Trailers>> dataTrailer = new MutableLiveData<>();


    private static Observable<MovieListResponse> movieListResponseObservable;
    private static Observable<MovieReviewResponse> movieReviewResponseObservable;
    private static Observable<MovieTrailerResponse> movieTrailerResponseObservable;
    private static CompositeDisposable com = new CompositeDisposable();


    public static void fetchData(String sort) {
        MovieApiService apiService = NetworkAdapter.getRetrofitInstance().create(MovieApiService.class);

        movieListResponseObservable = apiService.getMovies(sort, BuildConfig.API_KEY);

        com.add(movieListResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieListResponse>() {

                                   @Override
                                   public void onNext(MovieListResponse movieListResponse) {
                                       List<Movie> results = movieListResponse.getResults();
                                       data.postValue(results);

                                   }

                                   @Override
                                   public void onError(Throwable e) {

                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }
                ));

    }


    public static void fetchMovieReview(int movieID) {
        MovieApiService apiService = NetworkAdapter.getRetrofitInstance().create(MovieApiService.class);
        movieReviewResponseObservable = apiService.getMovieReviews(movieID, BuildConfig.API_KEY);

        com.add(movieReviewResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieReviewResponse>() {
                    @Override
                    public void onNext(MovieReviewResponse movieReviewResponse) {
                        List<Reviews> reviews = movieReviewResponse.getReviews();
                        dataReviews.postValue(reviews);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    public static void fetchMovieTrailer(int movieID) {
        MovieApiService apiService = NetworkAdapter.getRetrofitInstance().create(MovieApiService.class);

        movieTrailerResponseObservable = apiService.getMovieTrailers(movieID, BuildConfig.API_KEY);

        com.add(movieTrailerResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieTrailerResponse>() {
                    @Override
                    public void onNext(MovieTrailerResponse movieTrailerResponse) {
                        List<Trailers> trailers = movieTrailerResponse.getTrailers();
                        dataTrailer.postValue(trailers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }
    public static LiveData<List<Movie>> getIntData() {
        return data;
    }

    public static LiveData<List<Reviews>> getReviewsData() {
        return dataReviews;
    }

    public static LiveData<List<Trailers>> getTrailerData() {
        return dataTrailer;
    }
}
