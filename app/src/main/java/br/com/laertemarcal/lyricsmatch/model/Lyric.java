package br.com.laertemarcal.lyricsmatch.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Laerte on 06/06/2015.
 */
public class Lyric {

    private String body;

    public Lyric(JSONObject lyricJson) throws JSONException {
        body = lyricJson.getString("lyrics_body");
    }

    public String getBody() {
        return body;
    }
}
