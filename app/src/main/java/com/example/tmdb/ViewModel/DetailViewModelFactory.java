package com.example.tmdb.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tmdb.model.Repository.MovieRepository;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private final int movieId;
    Context mContext;
    MovieRepository mRepository;


    public DetailViewModelFactory(MovieRepository repository, int movieId, Context context) {
        this.movieId = movieId;
        mContext = context;
        mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailsViewModel(movieId, mContext);
    }

}


