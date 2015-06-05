package br.com.laertemarcal.lyricsmatch.services;

import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.laertemarcal.lyricsmatch.fragments.ArtistsFragment;
import br.com.laertemarcal.lyricsmatch.fragments.TracksFragment;
import br.com.laertemarcal.lyricsmatch.model.Artist;
import br.com.laertemarcal.lyricsmatch.model.Track;

/**
 * Created by Laerte on 02/06/2015.
 */
public class TracksService {

    private final String url = "https://musixmatchcom-musixmatch.p.mashape.com/wsr/1.1/track.search?f_has_lyrics=1&page=1&page_size=50&s_track_rating=desc&q_artist=";
    private final TracksFragment handler;

    public TracksService(TracksFragment handler) {
        this.handler = handler;
    }

    public void sendRequest(String params) {
        try {
            params = URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(handler.getActivity());

        handler.getSpinner().setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + params, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESPOSTA", response.toString());
                handler.getSpinner().setVisibility(View.GONE);
                try {
                    ArrayList<Track> tracks = new Track().getTracks(response);
                    handler.setTracksOnView(tracks);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRO", error.getMessage());
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("X-Mashape-Key", "GNAsMZZ7mXmshxaBfcMd2JDsvEQcp14v6vUjsnKSzuY5ovKZ7R");
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        queue.add(jsonArrayRequest);
    }

}
