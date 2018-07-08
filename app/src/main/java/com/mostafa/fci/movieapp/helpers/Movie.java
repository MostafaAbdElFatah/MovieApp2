package com.mostafa.fci.movieapp.helpers;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.mostafa.fci.movieapp.Constants.MovieConverter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mostafa AbdEl_Fatah on 7/9/2017.
 */

@Entity
public class Movie implements Serializable {

    @PrimaryKey @NonNull
    private int id;
    @ColumnInfo
    private String movie;
    @ColumnInfo
    private int    year;
    @ColumnInfo
    private float rating;
    @ColumnInfo
    private String duration;
    @ColumnInfo
    private String director;
    @ColumnInfo
    private String tagline;
    @SerializedName("cast")
    @ColumnInfo
    @TypeConverters(MovieConverter.class)
    private List<Cast> castList;
    @ColumnInfo
    private String image;
    @ColumnInfo
    private String trailer;
    @ColumnInfo
    private String story;
    @ColumnInfo
    private boolean favorite;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMovie() {
        return movie;
    }

    public int getYear() {
        return year;
    }

    public float getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }

    public String getTagline() {
        return tagline;
    }

    public String getImage() {
        return image;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getStory() {
        return story;
    }
    public static  class Cast implements  Serializable{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
