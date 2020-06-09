package com.example.tmdb.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.R;
import com.example.tmdb.model.Trailers;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MyViewHolder> {



    private List<Trailers> mMovieTrailerList;
    private ListItemClickListener mListItemClickListener;
    private Context context;

    //Interface

    public MovieTrailerAdapter(List<Trailers> movieTrailerList, ListItemClickListener listItemClickListener) {
        mMovieTrailerList = movieTrailerList;
        this.mListItemClickListener = listItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_trailer, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//       holder.bind(mMovieTrailerList.get(position));

        Trailers trailers = mMovieTrailerList.get(position);

        Picasso.get()
                .load("http://img.youtube.com/vi/" + trailers.getKey() + "/0.jpg")
                .into(holder.imgViewTrailer);

    }

    @Override
    public int getItemCount() {
        return mMovieTrailerList.size();
    }

    public interface ListItemClickListener {

        void onListItemClick(Trailers movieTrailer);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_View_trailer)
        ImageView imgViewTrailer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Trailers videoClick = mMovieTrailerList.get(adapterPosition);
            mListItemClickListener.onListItemClick(videoClick);

        }

        public void bind(Trailers trailers) {

            Picasso.get()
                    .load("http://img.youtube.com/vi/" + trailers.getKey() + "/0.jpg")
                    .into(imgViewTrailer);


        }
    }

}
