package com.example.jackieposter.spotifyviewer;

import java.util.List;

public class ArtistSelectedEvent {
    private List<Item> artists;
    private int position;


    public ArtistSelectedEvent(int position, List<Item> artists) {
        this.artists = artists;
        this.position = position;

    }

    public List<Item> getArtists() {
        return artists;
    }

    public int getPosition() {
        return position;
    }

}
