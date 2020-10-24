package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMovies;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();

        rvMovies = findViewById(R.id.rvMovies);
        final MovieAdapter adapter = new MovieAdapter(this, movies);
        rvMovies.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(layoutManager);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try{
                    JSONArray results = json.jsonObject.getJSONArray(("results"));
                    for (int i = 0; i < results.length(); i++) {
                        movies.add(new Movie(results.getJSONObject(i)));
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("MainActivity", movies.toString()+"");
                } catch(Exception e){
                    Log.e("MainActivity", e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.v("MainActivity", response);
            }
        });
    }
}