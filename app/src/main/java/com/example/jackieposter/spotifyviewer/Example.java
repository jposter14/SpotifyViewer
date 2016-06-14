
package com.example.jackieposter.spotifyviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("artists")
    @Expose
    private Artist artists;

    /**
     * 
     * @return
     *     The artists
     */
    public Artist getArtists() {
        return artists;
    }

    /**
     * 
     * @param artists
     *     The artists
     */
    public void setArtists(Artist artists) {
        this.artists = artists;
    }

}
