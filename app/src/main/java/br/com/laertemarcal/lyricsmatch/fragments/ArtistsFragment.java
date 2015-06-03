package br.com.laertemarcal.lyricsmatch.fragments;

/**
 * Created by Laerte on 02/06/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.adapters.ArtistsAdapter;

public class ArtistsFragment extends Fragment {

    private RecyclerView mArtistsRecyclerView;

    public ArtistsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mArtistsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mArtistsRecyclerView.setHasFixedSize(true);
        mArtistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] dataset = new String[] {
                "oi", "boys", "tudo", "legal"
        };
        mArtistsRecyclerView.setAdapter(new ArtistsAdapter(dataset));
        return rootView;
    }

}
