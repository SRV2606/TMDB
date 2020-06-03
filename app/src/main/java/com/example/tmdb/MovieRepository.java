package com.example.tmdb;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tmdb.Network.RemoteNetworkCall;
import com.example.tmdb.db.MovieDatabase;
import com.example.tmdb.db.dao.IFavouriteMovieDao;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.Reviews;
import com.example.tmdb.model.Trailers;

import java.util.List;

public class MovieRepository {

    private static final Object LOCK = new Object();
    private static MovieRepository sMoviesRepository;
    private int movieID;
    private IFavouriteMovieDao mMovieDao;
    private LiveData<List<Movie>> mData;
    private LiveData<List<Movie>> mDataFav;
    private LiveData<List<Reviews>> mReviewResult;
    private LiveData<List<Trailers>> mTrailerResult;

    //constructor for movie
    public MovieRepository(Application application) {

        MovieDatabase db = MovieDatabase.getInstance(application);
        mMovieDao = db.movieDAO();

        RemoteNetworkCall.fetchData("popular");

    }

    //constructor for review,trailer
    public MovieRepository(int id, Context context) {
        MovieDatabase db = MovieDatabase.getInstance(context);
        mMovieDao = db.movieDAO();
        this.movieID = id;
        RemoteNetworkCall.fetchMovieReview(movieID);
        RemoteNetworkCall.fetchMovieTrailer(movieID);

    }


    public synchronized static MovieRepository getInstance(Application application) {
        if (sMoviesRepository == null) {
            synchronized (LOCK) {
                sMoviesRepository = new MovieRepository(application);
            }
        }
        return sMoviesRepository;
    }


    public void getTopRated() {
        RemoteNetworkCall.fetchData("top_rated");
    }

    public void getPopular() {
        RemoteNetworkCall.fetchData("popular");
    }


    public void getUpcoming() {
        RemoteNetworkCall.fetchData("upcoming");
    }


    public LiveData<List<Movie>> mLiveData() {
        mData = RemoteNetworkCall.getIntData();

        return mData;
    }

    public void getFavData() {
        mDataFav = mMovieDao.loadAll();

    }

    public LiveData<List<Movie>> mLiveDataFav() {

        return mDataFav;
    }

    public LiveData<List<Reviews>> mReviewLiveData() {
        mReviewResult = RemoteNetworkCall.getReviewsData();

        return mReviewResult;
    }

    public LiveData<List<Trailers>> mTrailerLiveData() {
        mTrailerResult = RemoteNetworkCall.getTrailerData();

        return mTrailerResult;
    }


    public void insert(Movie result) {
        new insertAsyncTask(mMovieDao).execute(result);

    }


    class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private IFavouriteMovieDao mMovieDao;

        public insertAsyncTask(IFavouriteMovieDao movieDao) {

            mMovieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... results) {

            mMovieDao.insert(results[0]);

            return null;
        }
    }


//    public void deleteData(int id) {
//        new deleteAsyncTask(mMovieDao).execute(id);
//
//    }
//
//


}
