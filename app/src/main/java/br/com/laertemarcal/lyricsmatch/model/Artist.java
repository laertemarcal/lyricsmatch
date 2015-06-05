package br.com.laertemarcal.lyricsmatch.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Laerte on 04/06/2015.
 */
public class Artist {

    private int id;
    private String name;
    private ArrayList<String> primaryGenres;
    private int rating;

    public Artist() {

    }

    public Artist(JSONObject artistJson) throws JSONException {
        id = artistJson.getInt("artist_id");
        name = artistJson.getString("artist_name");


        JSONArray musicGenres = artistJson.getJSONObject("primary_genres").getJSONArray("music_genre");
        primaryGenres = new ArrayList<>();
        for (int i = 0; i < musicGenres.length(); i++) {
            primaryGenres.add(musicGenres.getJSONObject(i).getString("music_genre_name"));
        }

        rating = artistJson.getInt("artist_rating");
    }

    public ArrayList<Artist> getArtists(JSONArray artistJsonArray) throws JSONException {
        ArrayList<Artist> artists = new ArrayList<>();
        ArrayList<JSONObject> artistJsonObject = new ArrayList<>();
        for (int i = 0; i < artistJsonArray.length(); i++) {
            artistJsonObject.add(artistJsonArray.getJSONObject(i));
        }
        for (int i = 0; i < artistJsonObject.size(); i++) {
            artists.add(new Artist(artistJsonObject.get(i)));
        }
        return artists;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryGenres() {
        String genres = "";
        for (int i = 0; i < primaryGenres.size(); i++) {
            if (i < primaryGenres.size() - 1) {
                genres += primaryGenres.get(i) + ", ";
            } else {
                genres += primaryGenres.get(i);
            }
        }
        return genres;
    }

    public int getRating() {
        return rating;
    }
}
