package com.example.tmdb.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdb.MovieRepository;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.Reviews;
import com.example.tmdb.model.Trailers;

import java.util.List;

public class DetailsViewModel extends ViewModel {

    LiveData<List<Movie>> mData;
    LiveData<List<Reviews>> mReviewData;
    LiveData<List<Trailers>> mTrailerData;

    private MovieRepository mRespository;

    public DetailsViewModel(int id, Context context) {
        mRespository = new MovieRepository(id, context);

    }

    public void insert(Movie result) {
        mRespository.insert(result);
    }

    public LiveData<List<Movie>> getAllFav() {
        return mData;
    }

    public LiveData<List<Reviews>> getAllReviews() {
        mReviewData = mRespository.mReviewLiveData();
        return mReviewData;
    }

    public LiveData<List<Trailers>> getAllTrailers() {
        mTrailerData = mRespository.mTrailerLiveData();
        return mTrailerData;
    }


}
