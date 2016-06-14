package com.example.jackieposter.spotifyviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistDetail extends AppCompatActivity {
    @BindView(R.id.artist_name_field)
    TextView artistNameField;
    @BindView(R.id.artist_image)
    ImageView artistImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        ButterKnife.bind(this);

        Intent displayArtistDetailIntent = getIntent();
        String[] artistInfo = displayArtistDetailIntent.getStringArrayExtra(ArtistApplication.EXTRA_INFO);
        String artistName = artistInfo[0];
        String imageUrl = artistInfo[1];

        artistNameField.setText(artistName);

        int imageDimen = this.getResources().getDimensionPixelSize(R.dimen.artist_image_size);

        Picasso.with(this)
                .load(imageUrl)
                .resize(imageDimen,imageDimen)
                .centerCrop()
                .into(artistImage);

    }
}
