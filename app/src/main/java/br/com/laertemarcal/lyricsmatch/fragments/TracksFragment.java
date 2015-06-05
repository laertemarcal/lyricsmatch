package br.com.laertemarcal.lyricsmatch.fragments;

/**
 * Created by Laerte on 02/06/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.adapters.ArtistsAdapter;
import br.com.laertemarcal.lyricsmatch.adapters.TracksAdapter;
import br.com.laertemarcal.lyricsmatch.listeners.RecyclerItemClickListener;
import br.com.laertemarcal.lyricsmatch.model.Artist;
import br.com.laertemarcal.lyricsmatch.model.Track;
import br.com.laertemarcal.lyricsmatch.services.TracksService;

public class TracksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mSpinner;
    private String mArtistName;

    public TracksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArtistName = getArguments().getString("artist_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new TracksAdapter(new ArrayList<Track>()));

        mSpinner = (ProgressBar) rootView.findViewById(R.id.progressBar);

        TracksService ts = new TracksService(this);
        ts.sendRequest(mArtistName);

        return rootView;
    }

    public void setTracksOnView(ArrayList<Track> tracks) {
        mRecyclerView.setAdapter(new TracksAdapter(tracks));
    }

    public ProgressBar getSpinner() {
        return mSpinner;
    }

}
