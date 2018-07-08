package com.mostafa.fci.movieapp.helpers;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mostafa AbdEl_Fatah on 7/9/2017.
 */

public class HelpServices {
    // traillers
    static String traliers[]={  "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eOrNdBpGMv8\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zSWdZVtXT7E\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_rRoD28-WgU\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_PZpmTj1Q8Q\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y2rYRu8UW8M\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8CTjcVr9Iao\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/owK1qxDselE\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4sj1MT05lAA\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8-_9n5DtKOc\" frameborder=\"0\" allowfullscreen></iframe>"
            ,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zwhP5b4tD6g\" frameborder=\"0\" allowfullscreen></iframe>"};

    // get Json Object and return Array of Objects
   public static List<Movie> getMoviesList(String jsonString){
        if (jsonString == "" || jsonString == null){
            return null;
        }

       Random rand = new Random();

        // Parse Json
        List<Movie> movieList = new ArrayList<>();
        try{

            JSONObject parentObject = new JSONObject(jsonString);
            JSONArray parentArray = parentObject.getJSONArray("movies");
            // create list to set movies


            Gson gson = new Gson();
            for(int i=0; i<parentArray.length(); i++) {
                JSONObject jsonObject = parentArray.getJSONObject(i);
                //Movie movie= gson.fromJson(finalObject.toString(),Movie.class);

                //Above Line Equel all lines in comment
                Movie movie = new Movie();
                movie.setId(rand.nextInt(9999999));
                movie.setMovie(jsonObject.getString("movie"));
                movie.setYear(jsonObject.getInt("year"));
                movie.setRating((float) jsonObject.getDouble("rating"));
                movie.setDuration(jsonObject.getString("duration"));
                movie.setDirector(jsonObject.getString("director"));
                movie.setTagline(jsonObject.getString("tagline"));
                movie.setImage(jsonObject.getString("image"));
                movie.setStory(jsonObject.getString("story"));
                movie.setTrailer(traliers[i]);
                movie.setFavorite(false);
                JSONArray childArray = jsonObject.getJSONArray("cast");

                List<Movie.Cast> castList = new ArrayList<>();
                for (int j = 0; j < childArray.length(); j++) {
                    Movie.Cast cast = new Movie.Cast();
                    cast.setName(childArray.getJSONObject(j).getString("name"));
                    castList.add(cast);
                }
                movie.setCastList(castList);
                movieList.add(movie);
            }
        } catch (Exception e){

        }
        return  movieList;
    }
}