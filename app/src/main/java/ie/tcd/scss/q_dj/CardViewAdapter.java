package ie.tcd.scss.q_dj;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.DataObjectHolder> {
    private ArrayList<Song> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView song, artist, userID, upVoted;
        ImageView upVote;
        public DataObjectHolder(View itemView) {
            super(itemView);
            song = (TextView) itemView.findViewById(R.id.song_name);
            artist = (TextView) itemView.findViewById(R.id.artist_name);
            userID = (TextView) itemView.findViewById(R.id.userid);
            upVoted = (TextView) itemView.findViewById(R.id.upVoted);
            upVoted.setVisibility(View.GONE);
            upVote = (ImageView) itemView.findViewById(R.id.upVote);
            upVote.setClickable(true);
            upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upVote.setVisibility(View.GONE);
                    upVoted.setVisibility(View.VISIBLE);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        CardViewAdapter.myClickListener = myClickListener;
    }

    public CardViewAdapter(ArrayList<Song> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row, parent, false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.song.setText(mDataset.get(position).getTitle() + position);
        holder.artist.setText(mDataset.get(position).getArtist());
        holder.userID.setText(mDataset.get(position).getSpotifyID());
    }

    public void addItem(Song dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}