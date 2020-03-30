package com.example.projetofinalfilmes.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalfilmes.R;
import com.example.projetofinalfilmes.models.Season;
import com.example.projetofinalfilmes.models.Serie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonHolder> {

    private Context context;
    private ArrayList<Season> seasonsList;
    private SeasonAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        public void onItemClick(Context context, Season season);
    }

    public SeasonAdapter(ArrayList<Season> seasonsList, Context context, SeasonAdapter.OnItemClickListener listener){
        this.context = context;
        this.seasonsList = seasonsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeasonAdapter.SeasonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.season_item, parent, false);
        return new SeasonAdapter.SeasonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(context, seasonsList.get(position));
            }
        });

        holder.progressSeason.getProgressDrawable().setColorFilter(
                Color.parseColor("#ab47bc"), android.graphics.PorterDuff.Mode.MULTIPLY);

        holder.textSeason.setText(seasonsList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(context, seasonsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonsList.size();
    }

    // This is your ViewHolder class that helps to populate data to the view
    static class SeasonHolder extends RecyclerView.ViewHolder {

        private TextView textSeason;
        private ImageView checkImage;
        private ProgressBar progressSeason;

        SeasonHolder(View itemView) {
            super(itemView);
            textSeason = itemView.findViewById(R.id.seasonName);
            checkImage = itemView.findViewById(R.id.checkboxSeason);
            progressSeason = itemView.findViewById(R.id.progressBar);
        }
    }

}
