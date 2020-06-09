package com.example.tmdb.Helpers;

import android.app.Application;
import android.content.Context;

import com.example.tmdb.ViewModel.DetailViewModelFactory;
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

    public static DetailViewModelFactory provideDetailViewModelFactory(Application application, int movieId, Context context) {
        MovieRepository repository = provideRepository(application);
        return new DetailViewModelFactory(repository, movieId, context);
    }
}
