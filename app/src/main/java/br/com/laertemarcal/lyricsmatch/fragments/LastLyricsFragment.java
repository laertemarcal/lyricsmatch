package br.com.laertemarcal.lyricsmatch.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.laertemarcal.lyricsmatch.R;

/**
 * Created by Laerte on 06/06/2015.
 */
public class LastLyricsFragment extends Fragment {

    private TextView mTextViewTitle;
    private TextView mTextViewBody;

    private String mTitle;
    private String mBody;


    public LastLyricsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_last_lyrics, container, false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        mTitle = sharedPref.getString("lyrics_title", getResources().getString(R.string.unsaved_last_lyric_title));
        mBody = sharedPref.getString("lyrics_body", getResources().getString(R.string.unsaved_last_lyric_body));

        mTextViewTitle = (TextView) rootView.findViewById(R.id.textViewLastLyricTitle);
        mTextViewBody = (TextView) rootView.findViewById(R.id.textViewLastLyricBody);

        mTextViewTitle.setText(mTitle);
        mTextViewBody.setText(mBody);

        return rootView;
    }
}
