package com.mostafa.fci.movieapp.helpers;

import com.mostafa.fci.movieapp.Constants.URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mostafa AbdEl_Fatah on 11/4/2017.
 */

public class WebServices {

    public static String getMovieJson(){
        HttpURLConnection connection = null;
        BufferedReader buf = null;
        try {
            URL url = new URL(URLs.MOVIE_LIST);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            buf = new BufferedReader( new InputStreamReader(in) );
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ( (line = buf.readLine())!= null){
                stringBuilder.append(line);
            }
            return  stringBuilder.toString();
        }catch (Exception e){
            return "" ;
        }finally {
            if(connection !=null)
                connection.disconnect();
            if (buf !=null)
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
