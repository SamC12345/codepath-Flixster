package com.example.flixster;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    String title;
    String overview;
    String backdropPath;
    String posterPath;

    public Movie(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/"+posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/%s"+backdropPath;
    }
}