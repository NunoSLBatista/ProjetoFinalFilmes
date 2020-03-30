package com.example.projetofinalfilmes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetofinalfilmes.adapters.RecommendedAdapter;
import com.example.projetofinalfilmes.adapters.RecommendedAdapterSeries;
import com.example.projetofinalfilmes.adapters.SerieAdapter;
import com.example.projetofinalfilmes.data.DatabaseHandler;
import com.example.projetofinalfilmes.models.Genre;
import com.example.projetofinalfilmes.models.Movie;
import com.example.projetofinalfilmes.models.SearchResult;
import com.example.projetofinalfilmes.models.SearchResultSeries;
import com.example.projetofinalfilmes.models.Serie;
import com.example.projetofinalfilmes.models.VideoResult;
import com.example.projetofinalfilmes.network.GetDataService;
import com.example.projetofinalfilmes.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieDetailActivity extends AppCompatActivity implements RecommendedAdapterSeries.OnItemClickListener {

    Serie serie;
    ImageView backdropImage;
    ImageView posterImage;
    TextView titleText;
    TextView releaseText;
    TextView descriptionText;
    TextView dot1;
    TextView dot2;
    TextView duration;
    TextView rating;
    TextView company;
    RecyclerView recyclerView;
    LinearLayout playTrailer;

    private static String API_KEY = "7c0d1ae5e4fa7e4a562859f06f4c7c3a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detail);

        backdropImage = findViewById(R.id.backDropImage);
        posterImage = findViewById(R.id.posterImage);
        titleText = findViewById(R.id.movieTitle);
        releaseText = findViewById(R.id.releaseDate);
        descriptionText = findViewById(R.id.description);
        recyclerView = findViewById(R.id.relatedMovies);
        dot1 = findViewById(R.id.bullet1);
        dot2 = findViewById(R.id.bullet2);
        duration = findViewById(R.id.duration);
        rating = findViewById(R.id.ratingText);
        company = findViewById(R.id.company);
        playTrailer = findViewById(R.id.playTrailer);

        dot1.setText("\u2022");
        dot2.setText("\u2022");

        serie = (Serie) getIntent().getSerializableExtra("serie");

        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<VideoResult> call = retrofitInterface.getVideos(serie.getId() + "", API_KEY, "en-US");

                call.enqueue(new Callback<VideoResult>() {
                    @Override
                    public void onResponse(Call<VideoResult> call, Response<VideoResult> response) {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + response.body().getVideoArrayList().get(0).getKey())));

                    }

                    @Override
                    public void onFailure(Call<VideoResult> call, Throwable t) {

                    }
                });

            }
        });

        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + serie.getPosterUrl()).into(posterImage);
        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + serie.getBackDropUrl()).into(backdropImage);
        titleText.setText(serie.getName());
        posterImage.setClipToOutline(true);

        if(serie.getOverview().length() > 202){
            descriptionText.setText(serie.getOverview().substring(0, 202) + "...");
        } else {
            descriptionText.setText(serie.getOverview());
        }

        ArrayList<Genre> genreArrayList = new ArrayList<Genre>();
        String movieGenreQuery = "";


        for(int i = 0; i < serie.getGenreId().size(); i++){
            DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
            Genre genre = dh.getGenre(serie.getGenreId().get(i).toString());
            if(i == serie.getGenreId().size() - 1){
                if(genre != null){
                    movieGenreQuery += genre.getId() + "";
                    releaseText.append(genre.getName() + " ");
                }
            } else {
                if(genre != null){
                    movieGenreQuery += genre.getId() + ", ";
                    releaseText.append(genre.getName() + ", ");
                }
            }

            genreArrayList.add(genre);
        }

        GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SearchResultSeries> call = retrofitInterface.getRelatedSeries(serie.getId(), API_KEY, "en-US", "1");

        call.enqueue(new Callback<SearchResultSeries>() {
            @Override
            public void onResponse(@NonNull Call<SearchResultSeries> call, @NonNull Response<SearchResultSeries> response) {
                Log.d("URL", response.raw().request().url().toString());
                RecommendedAdapterSeries adapter = new RecommendedAdapterSeries(response.body().getSeriesArrayList(), getApplicationContext(), SerieDetailActivity.this::onItemClick);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                recyclerView.setAdapter(adapter);

                /*
                Call<Movie> call2 = retrofitInterface.getMovie(serie.getId() + "", API_KEY, "en-US");

                call2.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        serie = response.body();
                        duration.setText(String.valueOf(serie.getRuntime()) + "min");
                        rating.setText(serie.getRating().toString());
                        company.setText(serie.getCompanyArrayList().get(0).getName());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                }); */

            }

            @Override
            public void onFailure(@NonNull Call<SearchResultSeries> call, @NonNull Throwable t) {

            }
        });

    }
    @Override
    public void onItemClick(Context context, Serie serie) {

    }
}
