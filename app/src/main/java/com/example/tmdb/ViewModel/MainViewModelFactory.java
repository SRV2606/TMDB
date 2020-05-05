package com.example.tmdb.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tmdb.MovieRepository;

public final class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRepository mRepository;

    public MainViewModelFactory(MovieRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked cast")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(mRepository);
    }
}