package com.example.tmdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tmdb.Network.RemoteNetworkCall;
import com.example.tmdb.db.MovieDatabase;
import com.example.tmdb.db.dao.IFavouriteMovieDao;
import com.example.tmdb.model.Movie;

import java.util.List;

public class MovieRepository {

    private static final Object LOCK = new Object();
    private static MovieRepository sMoviesRepository;
    private IFavouriteMovieDao mMovieDao;
    private LiveData<List<Movie>> mData;

    public MovieRepository(Application application) {

        MovieDatabase db = MovieDatabase.getInstance(application);
        mMovieDao = db.movieDAO();

        RemoteNetworkCall.fetchData("popular");

    }


    public synchronized static MovieRepository getInstance(Application application) {
        if (sMoviesRepository == null) {
            synchronized (LOCK) {
                sMoviesRepository = new MovieRepository(application);
            }
        }
        return sMoviesRepository;
    }


    public LiveData<List<Movie>> mLiveData() {
        mData = RemoteNetworkCall.getIntData();

        return mData;
    }


}
