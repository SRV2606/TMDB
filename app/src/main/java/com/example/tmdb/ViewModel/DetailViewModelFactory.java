package com.example.tmdb.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private final int movieId;
    Context mContext;


    public DetailViewModelFactory(int movieId, Context context) {
        this.movieId = movieId;
        mContext = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailsViewModel(movieId, mContext);
    }

}


