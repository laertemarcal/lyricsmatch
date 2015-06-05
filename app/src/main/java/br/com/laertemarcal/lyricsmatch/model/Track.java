package br.com.laertemarcal.lyricsmatch.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Laerte on 04/06/2015.
 */
public class Track {

    private int id;
    private int artistId;
    private int rating;
    private int lyricsId;
    private int albumId;
    
    private String name;
    private String artistName;
    private String albumName;
    private String albumArtURL;

    private boolean hasLyrics;

    public Track() {

    }

    public Track(JSONObject trackJson) throws JSONException {

        id = trackJson.getInt("track_id");
        artistId = trackJson.getInt("artist_id");
        rating = trackJson.getInt("track_rating");
        lyricsId = trackJson.getInt("lyrics_id");
        albumId = trackJson.getInt("album_id");

        name = trackJson.getString("track_name");
        artistName = trackJson.getString("artist_name");
        albumName = trackJson.getString("album_name");
        albumArtURL = trackJson.getString("album_coverart_350x350");

        hasLyrics = trackJson.getBoolean("has_lyrics");

    }

    public ArrayList<Track> getTracks(JSONArray trackJsonArray) throws JSONException {
        ArrayList<Track> tracks = new ArrayList<>();
        ArrayList<JSONObject> trackJsonObject = new ArrayList<>();
        for (int i = 0; i < trackJsonArray.length(); i++) {
            trackJsonObject.add(trackJsonArray.getJSONObject(i));
        }
        for (int i = 0; i < trackJsonObject.size(); i++) {
            tracks.add(new Track(trackJsonObject.get(i)));
        }
        return tracks;
    }

    public int getId() {
        return id;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getRating() {
        return rating;
    }

    public int getLyricsId() {
        return lyricsId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtURL() {
        return albumArtURL;
    }

    public boolean isHasLyrics() {
        return hasLyrics;
    }
}
