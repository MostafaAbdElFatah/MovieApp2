package com.mostafa.fci.movieapp.Activitys;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mostafa.fci.movieapp.Adapters.MovieAdapter;
import com.mostafa.fci.movieapp.R;
import com.mostafa.fci.movieapp.helpers.ConnectionStatus;
import com.mostafa.fci.movieapp.helpers.HelpServices;
import com.mostafa.fci.movieapp.helpers.Movie;
import com.mostafa.fci.movieapp.helpers.RoomDatabase;
import com.mostafa.fci.movieapp.helpers.WebServices;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView moviesList = null;
    ProgressDialog dialog;
    MovieAdapter movieAdapter = null;
    List<Movie> movies = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Your message..");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please Wait ...");
        /******************************************************************************************/
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        /*******************************************************************************************/

        InitializeViews();
        // Check the Internet Connection
        boolean connected = ConnectionStatus.isNetworkAvailable(MainActivity.this);
        if (connected) {
            List<Movie> list = RoomDatabase.getDatabase(MainActivity.this).daoDatabase().fetchAllMovie();
            if (list.size() > 0 ) {
                for (Movie movie : list) {
                    movies.add(movie);
                }
            }else {
                // Load Data From Server
                new JsonTask().execute();
            }

        } else {
            showDialog("Internet Not Connected", "Please Connect to Internet Network .....");
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
            boolean connected = ConnectionStatus.isNetworkAvailable(MainActivity.this);
            if (connected) {
                // Load Data From Server
                new JsonTask().execute();
            } else {
                showDialog("Internet Not Connected", "Please Connect to Internet Network .....");
            }
            return true;

        }else if (id == R.id.favorite){
            startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public class JsonTask extends AsyncTask<Void, String, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<Movie> doInBackground(Void... param) {
            List<Movie> movieList = HelpServices.getMoviesList(WebServices.getMovieJson());
            if (movieList != null) {
                if (movieList.size() >= 1) {
                    return movieList;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Movie> list) {
            super.onPostExecute(list);
            if (list != null) {
                movies.addAll(list);
                RoomDatabase.getDatabase(MainActivity.this).daoDatabase().deleteAllMovie();
                RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultipleMovies(list);
                if (movieAdapter != null)
                    movieAdapter.notifyDataSetChanged();
            }
            dialog.dismiss();

        }
    }

    // show Dialog
    private AlertDialog.Builder builder;

    public void showDialog(String title, String message) {
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

        });
        builder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Open Setting Phone Activity
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Initialize View In activity
    private void InitializeViews() {
        moviesList = findViewById(R.id.listview);
        movieAdapter = new MovieAdapter(getApplicationContext(), R.layout.row, movies);
        moviesList.setAdapter(movieAdapter);
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                Movie movie = movies.get(position);
                intent.putExtra("movie",movie);
                startActivity(intent);

            }
        });
    }



}




