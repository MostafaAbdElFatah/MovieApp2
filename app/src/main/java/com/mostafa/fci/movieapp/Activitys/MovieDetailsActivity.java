package com.mostafa.fci.movieapp.Activitys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mostafa.fci.movieapp.R;
import com.mostafa.fci.movieapp.helpers.ConnectionStatus;
import com.mostafa.fci.movieapp.helpers.Movie;
import com.mostafa.fci.movieapp.helpers.RoomDatabase;

import org.w3c.dom.Text;

import java.io.Serializable;

public class MovieDetailsActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    Movie movie;
    WebView trailerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        floatingActionButton = findViewById(R.id.fab);
        TextView movieTextView = findViewById(R.id.movie);
        TextView taglineTextView = findViewById(R.id.tagline);
        TextView yearTextView = findViewById(R.id.year);
        TextView durationTextView = findViewById(R.id.duration);
        TextView directorTextView = findViewById(R.id.director);
        TextView castTextView = findViewById(R.id.cast);
        TextView storyTextView = findViewById(R.id.story);
        RatingBar ratingTextView = findViewById(R.id.rating);
        trailerTextView = findViewById(R.id.trailer);
        trailerTextView.getSettings().setJavaScriptEnabled(true);
        trailerTextView.setWebChromeClient(new WebChromeClient() {

        });
        movie = (Movie) getIntent().getSerializableExtra("movie");
        movie = RoomDatabase.getDatabase(MovieDetailsActivity.this).daoDatabase()
                .fetchOneMoviebyMovieId(movie.getId());
        movieTextView.setText(movie.getMovie());
        taglineTextView.setText(movie.getTagline());
        yearTextView.setText(String.valueOf(movie.getYear()));
        durationTextView.setText(movie.getDuration());
        directorTextView.setText(movie.getDirector());
        String casts = "";
        for (Movie.Cast cast : movie.getCastList()) {
            casts += cast.getName();
        }
        castTextView.setText(casts);
        storyTextView.setText(movie.getStory());
        trailerTextView.loadData(movie.getTrailer(), "text/html", "utf-8");
        ratingTextView.setRating(movie.getRating());
        if (movie.isFavorite()) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_red_24dp);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_border_red_24dp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Refresh) {
            // Check the Internet Connection
            return true;
        }else if (id == R.id.favorite){
            startActivity(new Intent(MovieDetailsActivity.this,FavoriteActivity.class));
        }else if (id == R.id.home){
            startActivity(new Intent(MovieDetailsActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        trailerTextView.onPause();
    }

    public void favoritebtnClicked(View view) {

        if (movie.isFavorite()) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            movie.setFavorite(false);
            RoomDatabase.getDatabase(MovieDetailsActivity.this).daoDatabase().updateMovie(movie);
        } else {
            movie.setFavorite(true);
            floatingActionButton.setImageResource(R.drawable.ic_favorite_red_24dp);
            RoomDatabase.getDatabase(MovieDetailsActivity.this).daoDatabase().updateMovie(movie);
        }
    }
}

