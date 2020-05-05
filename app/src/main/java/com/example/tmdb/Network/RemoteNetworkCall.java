package com.example.tmdb.Network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmdb.BuildConfig;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.responses.MovieListResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RemoteNetworkCall {

    private static MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    private static Observable<MovieListResponse> movieListResponseObservable;
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


    public static LiveData<List<Movie>> getIntData() {
        return data;
    }
}
