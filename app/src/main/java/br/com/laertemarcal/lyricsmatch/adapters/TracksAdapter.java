package br.com.laertemarcal.lyricsmatch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.model.Track;

/**
 * Created by Laerte on 02/06/2015.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private ArrayList<Track> mTracks;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTrackName;
        public ImageView mAlbumArt;

        public ViewHolder(View view) {
            super(view);
            mTrackName = (TextView) view.findViewById(R.id.tvTrackName);
            mAlbumArt = (ImageView) view.findViewById(R.id.ivAlbumArt);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TracksAdapter(ArrayList<Track> tracks) {
        mTracks = tracks;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String trackName = mTracks.get(position).getName();
        String albumArtURL = mTracks.get(position).getAlbumArtURL();

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTrackName.setText(trackName);
        if (albumArtURL != null && !("").equals(albumArtURL)) {
            Picasso.with(holder.mAlbumArt.getContext()).load(albumArtURL).error(R.drawable.placeholder_lyricsmatch_logo).placeholder(R.drawable.placeholder_lyricsmatch_logo).into(holder.mAlbumArt);
        } else {
            Picasso.with(holder.mAlbumArt.getContext()).load(R.drawable.placeholder_lyricsmatch_logo).into(holder.mAlbumArt);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public ArrayList<Track> getDataset() {
        return mTracks;
    }
}
