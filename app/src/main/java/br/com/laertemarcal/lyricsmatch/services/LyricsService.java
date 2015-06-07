package br.com.laertemarcal.lyricsmatch.services;

/**
 * Created by Laerte on 06/06/2015.
 */

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.fragments.LyricsFragment;
import br.com.laertemarcal.lyricsmatch.model.Lyric;

/**
 * Created by Laerte on 02/06/2015.
 */
public class LyricsService {

    private final String url = "https://musixmatchcom-musixmatch.p.mashape.com/wsr/1.1/track.lyrics.get?track_id=";
    private final LyricsFragment handler;
    private Lyric lyric;

    public LyricsService(LyricsFragment handler) {
        this.handler = handler;
    }

    public void sendRequest(String param) {
        try {
            param = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(handler.getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url + param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.toString().isEmpty() || response.length() == 0) {
                    Toast.makeText(handler.getActivity(), handler.getResources().getString(R.string.response_not_found), Toast.LENGTH_LONG).show();
                }
                handler.getSpinner().setVisibility(View.GONE);
                try {
                    lyric = new Lyric(response);
                    handler.saveLastLyric(lyric.getBody());
                    handler.getTextViewLyricsBody().setText(lyric.getBody());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRO", error.getMessage());
                Toast.makeText(handler.getActivity(), handler.getResources().getString(R.string.response_error), Toast.LENGTH_LONG).show();
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

        queue.add(jsonObjectRequest);
    }

}

