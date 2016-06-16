package com.example.jackieposter.spotifyviewer;

import android.app.Application;
import android.content.Intent;

import java.util.List;

import de.greenrobot.event.EventBus;

public class ArtistApplication extends Application {

    public final static String EXTRA_INFO = "com.example.jackieposter.spotifyviewer.INFO";
    public final static String NO_IMAGE_URL = "http://www.vishmax.com/en/innovattive-cms/themes/themax-theme-2015/images/no-image-found.gif";
    public static String artistName;
    public static String imageUrl;

    public ArtistApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ServiceManager.init();

        SpotifyArtistAdapter.bus.register(this);

    }

    public void onEvent(ArtistSelectedEvent event) {
        List<Item> artists = event.getArtists();
        int position = event.getPosition();

        Item artistClicked = artists.get(position);
        artistName = artistClicked.getName();

        List<Images> images = artistClicked.getImages();

        if (!images.isEmpty()) {
            Images image = images.get(0);
            imageUrl = image.getHref();
        }
        else {
            imageUrl = NO_IMAGE_URL;
        }

        String[] artistInfo = {artistName, imageUrl};

        Intent artistDetailIntent = new Intent(this, ArtistDetail.class);
        artistDetailIntent.putExtra(EXTRA_INFO, artistInfo);
        artistDetailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(artistDetailIntent);

    }

}
