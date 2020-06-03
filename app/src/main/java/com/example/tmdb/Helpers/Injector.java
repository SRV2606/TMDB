package com.example.tmdb.Helpers;

import android.app.Application;

import com.example.tmdb.ViewModel.MainViewModelFactory;
import com.example.tmdb.db.MovieDatabase;
import com.example.tmdb.model.Repository.MovieRepository;

public class Injector {


    private static MovieRepository provideRepository(Application context) {
        MovieDatabase database = MovieDatabase.getInstance(context.getApplicationContext());

        return MovieRepository.getInstance(context);
    }

    public static MainViewModelFactory provideMainViewModelFactory(Application application) {
        MovieRepository repository = provideRepository(application);
        return new MainViewModelFactory(repository);
    }
}
