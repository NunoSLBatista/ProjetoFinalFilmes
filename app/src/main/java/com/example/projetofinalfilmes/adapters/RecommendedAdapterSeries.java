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
import com.example.projetofinalfilmes.models.Serie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecommendedAdapterSeries extends RecyclerView.Adapter<RecommendedAdapterSeries.MovieHolder> {

    private Context context;
    private ArrayList<Serie> seriesList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        public void onItemClick(Context context, Serie serie);
    }


    public RecommendedAdapterSeries(ArrayList<Serie> movieList, Context context, OnItemClickListener listener){
        this.context = context;
        this.seriesList = movieList;
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
                listener.onItemClick(context, seriesList.get(position));
            }
        });

        int maxSize = 15;
        if(seriesList.get(position).getName().length() < 16){
            maxSize = seriesList.get(position).getName().length();
        }

        holder.titleMovieTextView.setText(seriesList.get(position).getName().substring(0, maxSize));
        holder.posterMovieImageView.setClipToOutline(true);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + seriesList.get(position).getPosterUrl()).into(holder.posterMovieImageView);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
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
