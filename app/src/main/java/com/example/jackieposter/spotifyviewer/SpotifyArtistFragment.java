package com.example.jackieposter.spotifyviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SpotifyArtistFragment extends Fragment {

    @BindView(R.id.artist_input)
    EditText artistInputView;
    @BindView(R.id.recyclerView)
    RecyclerView artistRecyclerView;

    private Artist artists = new Artist();
    private List<Item> artistList = new ArrayList<Item>();
    private SpotifyArtistAdapter artistAdapter;
    private List<Item> adapterList = new ArrayList<Item>();

    public SpotifyArtistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_spotify_artists, container, false);
        ButterKnife.bind(this, rootView);

        artistRecyclerView.setHasFixedSize(true);
        setRecyclerViewLayoutManager();
        setRecyclerViewAdapter();

        return rootView;
    }


    @OnClick(R.id.search_button)
    public void searchButtonClicked() {
        try {
            Log.d("Search Button clicks", "in search");
            loadArtists();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Hide keyboard
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setRecyclerViewAdapter() {
        artistAdapter = new SpotifyArtistAdapter(getContext(), adapterList);
        artistRecyclerView.setAdapter(artistAdapter);
    }

    private void setRecyclerViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        artistRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void loadArtists() throws IOException {
        Log.d("in load artists", "in load artists");
        String artistName = artistInputView.getText().toString();
        ServiceManager.Spotify.spotifyService.getArtist(artistName).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                artists = example.getArtists();
                artistList = artists.getItems();
                if (artistList != null) {
                    updateArtists(artistList);
                } else {
                    Timber.tag("onResponse").d("artist null");
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("in load artists", String.valueOf(t));
                Timber.tag("onResponse").d("failure");
            }
        });
    }


    private void updateArtists(@NonNull List<Item> artistList) {
        Log.d("updateArtists", "Got into updating artists");
        for(int i = 0; i < artistList.size(); i++){
            Log.d("updateArtists", "artist name: " + artistList.get(i).getName());
        }
        adapterList.clear();
        adapterList.addAll(artistList);
        artistAdapter.notifyDataSetChanged();
    }
}
