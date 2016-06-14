package com.example.jackieposter.spotifyviewer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import timber.log.Timber;

public class ServiceManager {
    public static void init() {
        Spotify.initService();
    }

    public static class Spotify {
        private static final String BASE_URL = "https://api.spotify.com/v1/";

        public static SpotifyService spotifyService;

        public static void initService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(getConverter())
                    .client(getClient())
                    .build();
            spotifyService = retrofit.create(SpotifyService.class);
        }

        private static Converter.Factory getConverter() {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();
            return GsonConverterFactory.create(gson);
        }

        private static OkHttpClient getClient() {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }

        public interface SpotifyService {
            @GET("search?type=artist")
            Call<Example> getArtist(@Query("q") String artistName);
        }
    }
}
