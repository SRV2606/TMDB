package com.example.tmdb.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdb.MovieRepository;
import com.example.tmdb.model.Movie;

import java.util.List;

public class MainViewModel extends ViewModel {


    private MovieRepository mMovieRepository;
    private LiveData<List<Movie>> mData;
    private LiveData<List<Movie>> mDataFav;


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

    public LiveData<List<Movie>> mLiveDataFav() {
        if (mDataFav == null) {
            mDataFav = new MutableLiveData<>();
        }
        mDataFav = mMovieRepository.mLiveDataFav();
        return mDataFav;
    }

    public void getTopRated() {
        mMovieRepository.getTopRated();
    }

    public void getPopular() {
        mMovieRepository.getPopular();
    }

    public void getFavData() {
        mMovieRepository.getFavData();
    }

    public void deleteData(int id) {

        // mMovieRepository.deleteData(id);


    }


}
