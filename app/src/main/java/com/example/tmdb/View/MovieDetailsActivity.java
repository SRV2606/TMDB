package com.example.tmdb.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.Helpers.RoundedTransformation;
import com.example.tmdb.R;
import com.example.tmdb.View.Adapters.MovieReviewAdapter;
import com.example.tmdb.View.Adapters.MovieTrailerAdapter;
import com.example.tmdb.ViewModel.DetailViewModelFactory;
import com.example.tmdb.ViewModel.DetailsViewModel;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.Network.Constants;
import com.example.tmdb.model.Reviews;
import com.example.tmdb.model.Trailers;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.app_bar_image)
    ImageView appBarImage;
    @BindView(R.id.collapsingtoolbar)
    CollapsingToolbarLayout collapsingtoolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recycler_review)
    RecyclerView recyclerReview;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.recycler_Trailers)
    RecyclerView recyclerTrailers;
    @BindView(R.id.image_poster)
    ImageView imagePoster;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.release)
    TextView release;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.plot)
    TextView plot;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.fab_detail)
    FloatingActionButton fabDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Movie mResult;
    private DetailsViewModel mDetailViewModel;
    private MovieReviewAdapter mMovieReviewAdapter;
    private MovieTrailerAdapter mMovieTrailerAdapter;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        fabDetail.setOnClickListener(this);
        fabDetail.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        postponeEnterTransition();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // for trailer adapter----------------------------------------------------------------------
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerTrailers.setLayoutManager(mLayoutManager);
        recyclerTrailers.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerTrailers.setItemAnimator(new DefaultItemAnimator());


        //for review adapter------------------------------------------------------------------------
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setAutoMeasureEnabled(true);
        recyclerReview.setLayoutManager(manager);
        recyclerReview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerReview.setItemAnimator(new DefaultItemAnimator());


        Movie movie = getIntent().getParcelableExtra("data");
        mResult = movie;
        String name = getIntent().getExtras().getString(MainActivity.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME);
        Float rating = Float.valueOf(movie.getVoteCount());
        collapsingtoolbar.setTitle(mResult.getOriginalTitle());
        DetailViewModelFactory factory = new DetailViewModelFactory(mResult.getId(), getApplicationContext());


        mDetailViewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel.class);
        Float cal = (5 * rating) / 10;

        ratingbar.setRating(cal);

        imagePoster.setTransitionName(name);
        Picasso.get().load(Constants.BACKDROP_BASE_URL + movie.getBackdropPath()).into(appBarImage);
        Picasso.get().load(Constants.POSTER_BASE_URL + mResult.getPosterPath()).transform(new RoundedTransformation(20, 0)).into(imagePoster, new Callback() {
            @Override
            public void onSuccess() {
                startPostponedEnterTransition();
            }

            @Override
            public void onError(Exception e) {
                startPostponedEnterTransition();
            }
        });


        title.setText(movie.getTitle());
        plot.setText(movie.getOverview());
        release.setText(movie.getReleaseDate());

        mDetailViewModel.getAllReviews().observe(this, new Observer<List<Reviews>>() {
            @Override
            public void onChanged(@Nullable List<Reviews> reviewResults) {
                Log.d("reviewsxxx", String.valueOf(reviewResults));
                mMovieReviewAdapter = new MovieReviewAdapter(reviewResults);
                recyclerReview.setAdapter(mMovieReviewAdapter);

            }
        });

        mDetailViewModel.getAllTrailers().observe(this, new Observer<List<Trailers>>() {
            @Override
            public void onChanged(@Nullable List<Trailers> trailerResults) {
                Log.d("trailerxxx", String.valueOf(trailerResults));
                mMovieTrailerAdapter = new MovieTrailerAdapter(trailerResults, new MovieTrailerAdapter.ListItemClickListener() {
                    @Override
                    public void onListItemClick(Trailers movieTrailer) {

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(Constants.YOUTUBE_TRAILER_BASE_URL + movieTrailer.getKey()));
                        startActivity(intent);


                    }
                });
                recyclerTrailers.setAdapter(mMovieTrailerAdapter);
            }
        });


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab_detail: {
                fabDetail.setImageDrawable(getDrawable(R.drawable.ic_favorite_true));
                mDetailViewModel.insert(mResult);
                Toast.makeText(this, mResult.getOriginalTitle() + " " + "added to favorites!!", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }


}
