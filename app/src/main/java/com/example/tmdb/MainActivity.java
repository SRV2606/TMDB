package com.example.tmdb;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.Adapters.MovieAdapter;
import com.example.tmdb.ViewModel.MainViewModel;
import com.example.tmdb.ViewModel.MainViewModelFactory;
import com.example.tmdb.model.Movie;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String MENU_SELECTED = "selected";


    MainViewModel viewModel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_lifeScript)
    TextView txtLifeScript;
    @BindView(R.id.collapsing_main)
    CollapsingToolbarLayout collapsingMain;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView movieRecycler;
    private int selected = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModelFactory vMainViewModelFactory = Injector.provideMainViewModelFactory(getApplication());
        viewModel = ViewModelProviders.of(this, vMainViewModelFactory).get(MainViewModel.class);
        ButterKnife.bind(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        movieRecycler.setLayoutManager(gridLayoutManager);


        if (savedInstanceState == null) {
            populateUI(selected);

        } else {
            selected = savedInstanceState.getInt(MENU_SELECTED);
            populateUI(selected);
        }


    }

    private void populateUI(int i) {


        // viewModel.mLiveDataFav().removeObservers(this);


        if (i == 0) {

            viewModel.mLiveData().observe(this, new Observer<List<Movie>>() {

                @Override
                public void onChanged(List<Movie> movies) {
                    setupRecyclerView(movies);
                }
            });


        }

    }

    private void setupRecyclerView(List<Movie> results) {
        if (results != null) {
            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), results);
            movieRecycler.setAdapter(adapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_SELECTED, selected);
        super.onSaveInstanceState(outState);
    }

}
