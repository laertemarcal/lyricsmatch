package br.com.laertemarcal.lyricsmatch.fragments;

/**
 * Created by Laerte on 02/06/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.adapters.TracksAdapter;
import br.com.laertemarcal.lyricsmatch.listeners.RecyclerItemClickListener;
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
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TracksAdapter tracksAdapter = (TracksAdapter) mRecyclerView.getAdapter();
                        ArrayList<Track> tracks = tracksAdapter.getDataset();
                        Track track = tracks.get(position);

                        Bundle args = new Bundle();
                        args.putInt("track_id", track.getId());
                        args.putString("track_artist", track.getArtistName());
                        args.putString("track_name", track.getName());

                        LyricsFragment lyricsFragment = new LyricsFragment();
                        lyricsFragment.setArguments(args);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack if needed
                        transaction.replace(R.id.container, lyricsFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();
                    }
                })
        );

        mSpinner = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mSpinner.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        TracksService ts = new TracksService(this);
        ts.sendRequest(mArtistName);

        return rootView;
    }

    public void setTracksOnView(ArrayList<Track> tracks) {
        mRecyclerView.swapAdapter(new TracksAdapter(tracks), false);
    }

    public ProgressBar getSpinner() {
        return mSpinner;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

}
