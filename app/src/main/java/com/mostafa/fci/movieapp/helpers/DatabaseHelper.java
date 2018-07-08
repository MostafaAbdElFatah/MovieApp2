package com.mostafa.fci.movieapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mostafa.fci.movieapp.Constants.DatabaseConstants;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public DatabaseHelper(Context context){
        super(context, DatabaseConstants.Database_Name,null,DatabaseConstants.Databse_Version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context,"created.....",Toast.LENGTH_LONG).show();
        try {

            //create tables in sqllite database
            db.execSQL(DatabaseConstants.MOVIE_TABLE_SQL);
            db.execSQL(DatabaseConstants.CAST_TABLE_SQL);

        }catch (Exception e){
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context,"upgrade.....",Toast.LENGTH_LONG).show();
        try {
            db.execSQL(DatabaseConstants.dropTable(DatabaseConstants.MOVIE_TABLE_SQL));
            db.execSQL(DatabaseConstants.dropTable(DatabaseConstants.CAST_TABLE_SQL));
            onCreate(db);
            Toast.makeText(context, "created table after drop" ,Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            Toast.makeText(context, e.getMessage() ,Toast.LENGTH_LONG).show();
        }

    }

    public boolean InsertMovies(List<Movie> list){

        SQLiteDatabase db = this.getWritableDatabase();
        for (int j = 0 ; j < list.size(); j++){
            ContentValues insert = new ContentValues();

            insert.put(DatabaseConstants.Movie_ID_Field,"");
            insert.put(DatabaseConstants.Movie_Name_Field,list.get(j).getMovie());
            insert.put(DatabaseConstants.Year_Field,list.get(j).getYear());
            insert.put(DatabaseConstants.Rating_Field,list.get(j).getRating());
            insert.put(DatabaseConstants.Duration_Field,list.get(j).getDuration());
            insert.put(DatabaseConstants.Director_Field,list.get(j).getDirector());
            insert.put(DatabaseConstants.Tagline_Field,list.get(j).getTagline());
            insert.put(DatabaseConstants.Image_Url_Field,list.get(j).getImage().toString());
            insert.put(DatabaseConstants.Trailer_Url_Field ,list.get(j).getTrailer());
            insert.put(DatabaseConstants.Story_Field , list.get(j).getStory());

            long n = db.insert(DatabaseConstants.Movie_Table , null , insert);
            Toast.makeText(context, "inserte n = " + n ,Toast.LENGTH_LONG).show();

            if (n == -1)
                return false;
        }
        return  true;
    }
}
