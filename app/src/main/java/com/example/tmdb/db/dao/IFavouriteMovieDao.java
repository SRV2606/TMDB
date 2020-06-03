package com.example.tmdb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tmdb.model.Movie;

import java.util.List;


@Dao
public interface IFavouriteMovieDao {

    @Query("SELECT * FROM favourite_movie")
    LiveData<List<Movie>> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie result);


    @Query("DELETE FROM favourite_movie WHERE id=:id")
    void delete(int id);






//    @Query("SELECT * FROM favourite_movie WHERE moid = :id AND movie_title = :title")
//    Movie checkForFavs(int id, String title);

}
