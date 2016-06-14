package com.example.jackieposter.spotifyviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("url")
    @Expose
    private String href;
    @SerializedName("width")
    @Expose
    private Integer width;

    public String getHref() {

        return href;
    }


    public Integer getHeight() {

        return height;
    }

    public Integer getWidth() {
        return width;
    }
}