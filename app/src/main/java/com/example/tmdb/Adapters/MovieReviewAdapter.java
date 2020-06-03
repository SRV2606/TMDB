package com.example.tmdb.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.R;
import com.example.tmdb.model.Reviews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MyViewHolder> {


    List<Reviews> mMovieReviews;

    public MovieReviewAdapter(List<Reviews> movieReviews) {

        this.mMovieReviews = movieReviews;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_review, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.bind(mMovieReviews.get(position));

    }

    @Override
    public int getItemCount() {
        return mMovieReviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtAuthor)
        TextView txtAuthor;
        @BindView(R.id.txtReview)
        TextView txtReview;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(Reviews reviews) {
            txtAuthor.setText(reviews.getAuthor());
            txtReview.setText(reviews.getContent());
        }
    }
}
