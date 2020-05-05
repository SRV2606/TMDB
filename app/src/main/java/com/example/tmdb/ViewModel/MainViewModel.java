package com.example.tmdb.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdb.MovieRepository;
import com.example.tmdb.model.Movie;

import java.util.List;

public class MainViewModel extends ViewModel {


    private MovieRepository mMovieRepository;
    private LiveData<List<Movie>> mData;


    public MainViewModel(MovieRepository repository) {
        mMovieRepository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<List<Movie>> mLiveData() {
        mData = mMovieRepository.mLiveData();
        return mData;
    }
}
