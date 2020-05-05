package com.example.tmdb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.R;
import com.example.tmdb.RoundedTransformation;
import com.example.tmdb.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tmdb.Network.Constants.POSTER_BASE_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private final List<Movie> mMovieList;
    private final Context mContext;


    public MovieAdapter(Context context, List<Movie> movieList) {
        mMovieList = movieList;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (mMovieList != null)
            holder.bind(mMovieList.get(position));

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.movie_nameTV)
        TextView movieNameTV;
        @BindView(R.id.movie_ratingTV)
        TextView movieRatingTV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Movie movie) {

            Picasso.get()
                    .load(POSTER_BASE_URL + movie.getPosterPath())
                    .transform(new RoundedTransformation(14, 0))
                    .into(imageView);
            movieNameTV.setText(movie.getTitle());
            String rating = " " + movie.getVoteAverage() + " ";
            movieRatingTV.setText(rating);
        }
    }
}
