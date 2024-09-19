package com.example.practicemoviesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private List<Movie> movies;
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.movies = movies;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movieitem, parent, false);
        }

        Movie currentMovie = movies.get(position);
        ImageView posterView = convertView.findViewById(R.id.image_id);
        TextView titleView = convertView.findViewById(R.id.movie_name_list);
        TextView ratingView = convertView.findViewById(R.id.rating);
        TextView descriptionView = convertView.findViewById(R.id.description);

        // Set movie data in the views
        titleView.setText(currentMovie.getTitle());
        ratingView.setText(String.valueOf(currentMovie.getRating()));
        descriptionView.setText(currentMovie.getDescription());

        // Load movie poster image using Picasso
        Picasso.get().load(currentMovie.getPosterUrl()).into(posterView);

        return convertView;
    }
}
