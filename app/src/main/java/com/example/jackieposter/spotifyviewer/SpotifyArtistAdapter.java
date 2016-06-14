package com.example.jackieposter.spotifyviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class SpotifyArtistAdapter extends RecyclerView.Adapter<SpotifyArtistAdapter.ViewHolder> {

    public static EventBus bus = new EventBus();

    private Context context;
    private List<Item> artists;
    private Item currItem;


    public SpotifyArtistAdapter(Context context, List<Item> artists) {
        this.context = context;
        this.artists = artists;

    }

    @Override
    public SpotifyArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpotifyArtistAdapter.ViewHolder holder, int position) {
        currItem = artists.get(position);
        holder.configureViews(currItem);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.artist_name)
        TextView artistNameView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bus.post(new ArtistSelectedEvent(ViewHolder.this.getAdapterPosition(), artists));
                }
            });
        }

        public void configureViews(Item item) {
            Log.d("in configure view", "in configure view");

            Log.d("artist name: ", item.getName() );
            artistNameView.setText(item.getName());
        }

    }

}

