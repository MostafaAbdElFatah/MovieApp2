package com.mostafa.fci.movieapp.Constants;
/**
 * Created by Mostafa AbdEl_Fatah on 9/22/2016.
 */
public class DatabaseConstants {

    final public static int Databse_Version        =1;
    final public static String Database_Name       ="db";
    final public static String ID_Field            ="id";
    final public static String Movie_Name_Field    ="movie";
    final public static String Year_Field          ="year";
    final public static String Rating_Field        ="rating";
    final public static String Duration_Field      ="duration";
    final public static String Director_Field      ="director";
    final public static String Tagline_Field       ="tagline";
    final public static String Image_Url_Field     ="image";
    final public static String Trailer_Url_Field   ="trailer";
    final public static String Movie_ID_Field      ="movieid";
    final public static String Cast_Name_Field     ="cast";
    final public static String Story_Field         ="story";
    final public static String Movie_Table         = "movie";
    final public static String Cast_Table          =  "cast";

    final public static String MOVIE_TABLE_SQL   =
            "CREATE TABLE " + Movie_Table + " ( " + ID_Field + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Movie_Name_Field + " TEXT NOT NULL , " + Year_Field + " INTEGER NOT NULL , " + Rating_Field +
                    " REAL NOT NULL , " + Duration_Field + " TEXT NOT NULL , " + Director_Field +
                    " TEXT NOT NULL , " + Tagline_Field + " TEXT NOT NULL" + Image_Url_Field +
                    " TEXT NOT NULL , " + Trailer_Url_Field +" TEXT NOT NULL ,"+ Story_Field +" TEXT NOT NULL )";

    final public static String CAST_TABLE_SQL   =
            "CREATE TABLE " + Cast_Table + " ( " + ID_Field + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Cast_Name_Field +" TEXT NOT NULL ," + Movie_ID_Field + " INTEGER NOT NULL)";

    public static String dropTable (String tableName){
        return "DROP TABLE IF EXISTS " + tableName ;
    }


}
