package com.example.projetofinalfilmes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalfilmes.R;
import com.example.projetofinalfilmes.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MovieHolder> {

    private Context context;
    private ArrayList<Movie> movieList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        public void onItemClick(Context context, Movie movie);
    }


    public RecommendedAdapter(ArrayList<Movie> movieList, Context context, OnItemClickListener listener){
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.recommended_movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(context, movieList.get(position));
            }
        });

        int maxSize = 15;
        if(movieList.get(position).getName().length() < 16){
            maxSize = movieList.get(position).getName().length();
        }

        holder.titleMovieTextView.setText(movieList.get(position).getName().substring(0, maxSize));
        holder.posterMovieImageView.setClipToOutline(true);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + movieList.get(position).getPosterUrl()).into(holder.posterMovieImageView);
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
