package com.example.projetofinalfilmes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalfilmes.R;
import com.example.projetofinalfilmes.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private ArrayList<Movie> movieList;

    public MovieAdapter(ArrayList<Movie> movieList, Context context){
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.titleMovieTextView.setText(movieList.get(position).getName());
        Picasso.with(context).load(movieList.get(position).getPosterUrl()).into(holder.posterMovieImageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // This is your ViewHolder class that helps to populate data to the view
    static class MovieHolder extends RecyclerView.ViewHolder {

        private TextView titleMovieTextView;
        private ImageView posterMovieImageView;

        MovieHolder(View itemView) {
            super(itemView);
            titleMovieTextView = itemView.findViewById(R.id.movieTitle);
            posterMovieImageView = itemView.findViewById(R.id.moviePoster);
        }
    }

}
