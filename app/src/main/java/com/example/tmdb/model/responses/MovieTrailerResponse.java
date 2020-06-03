package com.example.tmdb.model.responses;

import com.example.tmdb.model.Trailers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailerResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private List<Trailers> moviesTrailers;

    public int getId() {
        return id;
    }

    public List<Trailers> getTrailers() {
        return moviesTrailers;
    }
}
