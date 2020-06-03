package com.example.tmdb.Network;

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
    /**
     * Api Interface that facilitates request calls to TMDB Api and returns response similaro to
     * model in cast
     *
     * @param apiKey   Api Key from TMDB
     * @param language Based on language preference
     * @param page     page to load ,min 1 max 1000
     * @return json response
     */

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<MovieListResponse> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewResponse> getMovieReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailerResponse> getMovieTrailers(
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
