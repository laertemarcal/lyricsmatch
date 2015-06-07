package br.com.laertemarcal.lyricsmatch.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.services.LyricsService;

/**
 * Created by Laerte on 06/06/2015.
 */
public class LyricsFragment extends Fragment {
    private ProgressBar mSpinner;
    private TextView mTextViewTrackTitle;
    private TextView mTextViewLyricsBody;
    private int mTrackId;
    private String mTrackArtist;
    private String mTrackName;
    private String mLyricsBody;

    public LyricsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTrackId = getArguments().getInt("track_id");
            mTrackArtist = getArguments().getString("track_artist");
            mTrackName = getArguments().getString("track_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lyrics, container, false);

        mSpinner = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        mTextViewTrackTitle = (TextView) rootView.findViewById(R.id.tvTrackTitle);
        mTextViewLyricsBody = (TextView) rootView.findViewById(R.id.tvLyricsBody);
        mTextViewLyricsBody.setMovementMethod(new ScrollingMovementMethod());

        mSpinner.setVisibility(View.VISIBLE);

        new LyricsService(this).sendRequest(Integer.toString(mTrackId));

        mTextViewTrackTitle.setText(mTrackArtist + " - " + mTrackName);

        return rootView;
    }

    public TextView getTextViewLyricsBody() {
        return mTextViewLyricsBody;
    }

    public ProgressBar getSpinner() {
        return mSpinner;
    }

    public TextView getTextViewTrackTitle() {
        return mTextViewTrackTitle;
    }

    public void saveLastLyric(String body) {

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lyrics_title", mTrackArtist + " - " + mTrackName);
        editor.putString("lyrics_body", body);
        editor.commit();

    }
}
