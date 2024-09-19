package com.example.practicemoviesearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    private static final String API_KEY = "b2c97b1c";
    private static final String API_URL = "http://www.omdbapi.com";

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Button searchButton = findViewById(R.id.submit_btn);
        ListView listView = findViewById(R.id.listview_list);
        EditText nameText = findViewById(R.id.moviename);

        arrayAdapter = new MovieAdapter(this, movies);
        listView.setAdapter(arrayAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = nameText.getText().toString();
                fetchMovieList(movieName);
            }
        });
    }

    private void fetchMovieList(String movieName) {
        RequestParams params = new RequestParams();
        params.add("apikey", API_KEY);
        params.add("s", movieName);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray items = response.getJSONArray("Search");
                    movies.clear();

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject singleItem = items.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(singleItem.getString("Title"));
                        movie.setPosterUrl(singleItem.getString("Poster"));
                        fetchMovieDetails(singleItem.getString("imdbID"), movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // Handle the failure
            }
        });
    }

    private void fetchMovieDetails(String imdbID, Movie movie) {
        RequestParams movieDetailsParams = new RequestParams();
        movieDetailsParams.add("apikey", API_KEY);
        movieDetailsParams.add("i", imdbID);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, movieDetailsParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject detailsResponse) {
                try {
                    movie.setDescription(detailsResponse.getString("Plot"));
                    movie.setRating((float) detailsResponse.getDouble("imdbRating"));
                    movies.add(movie);

                    // Update the adapter
                    arrayAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // Handle the failure
            }
        });
    }
}
