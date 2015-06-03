package br.com.laertemarcal.lyricsmatch.services;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.fragments.ArtistsFragment;

/**
 * Created by Laerte on 02/06/2015.
 */
public class ArtistsService {

    private final String url = "https://musixmatchcom-musixmatch.p.mashape.com/wsr/1.1/artist.search?q_artist=";
    private Fragment handler;

    public ArtistsService(ArtistsFragment handler) {
        this.handler = handler;
    }

    public void sendRequest(String params) {
        RequestQueue queue = Volley.newRequestQueue(handler.getView().getContext());

        final ProgressBar spinner = (ProgressBar) handler.getView().findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + params, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESPOSTA", response.toString());
                spinner.setVisibility(View.GONE);
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
