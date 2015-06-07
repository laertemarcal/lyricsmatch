package br.com.laertemarcal.lyricsmatch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.model.Artist;

/**
 * Created by Laerte on 02/06/2015.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {
    private ArrayList<Artist> mArtists;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mArtistName;
        public TextView mArtistGenre;
        public ViewHolder(View v) {
            super(v);
            mArtistName = (TextView) v.findViewById(R.id.tvArtistName);
            mArtistGenre = (TextView) v.findViewById(R.id.tvArtistGenre);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ArtistsAdapter(ArrayList<Artist> artists) {
        mArtists = artists;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artist, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mArtistName.setText(mArtists.get(position).getName());
        holder.mArtistGenre.setText(mArtists.get(position).getPrimaryGenres());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public ArrayList<Artist> getDataset() {
        return mArtists;
    }
}
