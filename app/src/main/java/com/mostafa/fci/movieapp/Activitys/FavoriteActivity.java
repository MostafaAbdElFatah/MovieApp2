package com.mostafa.fci.movieapp.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mostafa.fci.movieapp.Adapters.MovieAdapter;
import com.mostafa.fci.movieapp.R;
import com.mostafa.fci.movieapp.helpers.Movie;
import com.mostafa.fci.movieapp.helpers.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    ListView moviesList;
    ProgressDialog dialog;
    MovieAdapter movieAdapter = null;
    List<Movie> movies = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        moviesList = findViewById(R.id.listview);
        List<Movie> list = RoomDatabase.getDatabase(FavoriteActivity.this).daoDatabase().fetchAllMovie();
        if (list.size() > 0 )
            for (Movie movie:list) {
                if (movie.isFavorite())
                    movies.add(movie);
            }
        movieAdapter = new MovieAdapter(getApplicationContext(), R.layout.row, movies);
        moviesList.setAdapter(movieAdapter);
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriteActivity.this, MovieDetailsActivity.class);
                Movie movie = movies.get(position);
                intent.putExtra("movie",movie);
                startActivity(intent);

            }
        });
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
        }else if (id == R.id.home){
            startActivity(new Intent(FavoriteActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
