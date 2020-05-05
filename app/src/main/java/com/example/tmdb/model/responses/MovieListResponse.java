package com.example.tmdb.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tmdb.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MovieListResponse implements Parcelable {

    public static final Parcelable.Creator<MovieListResponse> CREATOR = new Parcelable.Creator<MovieListResponse>() {
        @Override
        public MovieListResponse createFromParcel(Parcel source) {
            return new MovieListResponse(source);
        }

        @Override
        public MovieListResponse[] newArray(int size) {
            return new MovieListResponse[size];
        }
    };
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public MovieListResponse() {
    }

    protected MovieListResponse(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = new ArrayList<Movie>();
        in.readList(this.results, Movie.class.getClassLoader());
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeValue(this.totalResults);
        dest.writeValue(this.totalPages);
        dest.writeList(this.results);
    }


}
