package com.example.tmdb.model.Network;

import com.example.tmdb.model.Movie;
import com.example.tmdb.model.responses.MovieListResponse;
import com.example.tmdb.model.responses.MovieReviewResponse;
import com.example.tmdb.model.responses.MovieTrailerResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {


    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );


    @GET("movie/{filter}")
    Observable<MovieListResponse> getMovies(@Path("filter") String filter, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<MovieReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

    // query for movie trailers

    @GET("movie/{id}/videos")
    Observable<MovieTrailerResponse> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

}
