package com.mostafa.fci.movieapp.Constants;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mostafa.fci.movieapp.helpers.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FCI on 2018-06-22.
 */
@Dao
public interface DaoDatabase {

    @Insert
    void insertOnlySingleMovie(Movie movie);
    @Insert
    void insertMultipleMovies(List<Movie> movieList);
    @Query ("SELECT * FROM Movie WHERE id = :id")
    Movie fetchOneMoviebyMovieId(int id);
    @Query ("SELECT * FROM Movie")
    List<Movie> fetchAllMovie();
    @Update
    void updateMovie(Movie movie);
    @Delete
    void deleteMovie(Movie movie);
    @Query("DELETE FROM Movie")
    void deleteAllMovie();

}
