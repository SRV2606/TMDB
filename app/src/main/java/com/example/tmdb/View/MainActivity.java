package com.example.tmdb.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.Helpers.Injector;
import com.example.tmdb.R;
import com.example.tmdb.View.Adapters.MovieAdapter;
import com.example.tmdb.ViewModel.MainViewModel;
import com.example.tmdb.ViewModel.MainViewModelFactory;
import com.example.tmdb.model.Movie;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "animal_image_transition_name";
    private static final String MENU_SELECTED = "selected";


    MainViewModel viewModel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_main)
    CollapsingToolbarLayout collapsingMain;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView movieRecycler;
    private int selected = 0;
    private long max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModelFactory vMainViewModelFactory = Injector.provideMainViewModelFactory(getApplication());
        viewModel = ViewModelProviders.of(this, vMainViewModelFactory).get(MainViewModel.class);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        movieRecycler.setLayoutManager(gridLayoutManager);
        movieRecycler.setItemAnimator(new DefaultItemAnimator());
        movieRecycler.setNestedScrollingEnabled(true);

        if (savedInstanceState == null) {
            populateUI(selected);

        } else {
            selected = savedInstanceState.getInt(MENU_SELECTED);
            populateUI(selected);
        }


    }

    private void populateUI(int i) {




        if (i == 0) {

            viewModel.mLiveData().observe(this, new Observer<List<Movie>>() {

                @Override
                public void onChanged(List<Movie> movies) {
                    setupRecyclerView(movies);
                }
            });


        }
        if (i == 1) {

            viewModel.mLiveDataFav().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    setupRecyclerView(movies);
                }
            });
        }

    }


    private void setupRecyclerView(List<Movie> results) {

        viewModel.getProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.GONE);
                }


            }
        });
        if (results != null) {
            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), results, new MovieAdapter.ListItemClickListener() {
                @Override
                public void onListItemClick(Movie movie) {
                    Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                    intent.putExtra("data", movie);
                    startActivity(intent);
                    postponeEnterTransition();
                }
            });
            movieRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_SELECTED, selected);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.highest_Rated:

                viewModel.getTopRated();
                selected = 0;
                populateUI(selected);

                break;

            case R.id.most_popular:

                viewModel.getPopular();
                selected = 0;
                populateUI(selected);
                break;

            case R.id.fav:


                viewModel.getFavData();
                selected = 1;
                populateUI(selected);

                break;
        }

        return super.onOptionsItemSelected(item);

    }

}

