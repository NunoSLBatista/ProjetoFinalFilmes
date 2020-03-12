package com.example.projetofinalfilmes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetofinalfilmes.adapters.MovieAdapter;
import com.example.projetofinalfilmes.adapters.RecommendedAdapter;
import com.example.projetofinalfilmes.data.DatabaseHandler;
import com.example.projetofinalfilmes.models.Genre;
import com.example.projetofinalfilmes.models.GenreResult;
import com.example.projetofinalfilmes.models.Movie;
import com.example.projetofinalfilmes.models.SearchResult;
import com.example.projetofinalfilmes.models.VideoResult;
import com.example.projetofinalfilmes.network.GetDataService;
import com.example.projetofinalfilmes.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    Movie movie;
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

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

        movie = (Movie) getIntent().getSerializableExtra("movie");

        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<VideoResult> call = retrofitInterface.getVideos(movie.getId() + "", API_KEY, "en-US");

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



        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterUrl()).into(posterImage);
        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + movie.getBackDropUrl()).into(backdropImage);
        titleText.setText(movie.getName());
        posterImage.setClipToOutline(true);

        if(movie.getOverview().length() > 202){
            descriptionText.setText(movie.getOverview().substring(0, 202) + "...");
        } else {
            descriptionText.setText(movie.getOverview());
        }



        ArrayList<Genre> genreArrayList = new ArrayList<Genre>();
        String movieGenreQuery = "";

        for(int i = 0; i < movie.getGenreId().size(); i++){
            DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
            Genre genre = dh.getGenre(movie.getGenreId().get(i).toString());
            if(i == movie.getGenreId().size() - 1){
                movieGenreQuery += genre.getId() + "";
                releaseText.append(genre.getName() + " ");
            } else {
                movieGenreQuery += genre.getId() + ", ";
                releaseText.append(genre.getName() + ", ");
            }

            genreArrayList.add(genre);
        }

        GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SearchResult> call = retrofitInterface.getRelated("en-US", API_KEY, "false", "false", movieGenreQuery, "vote_count.desc", "1");

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
                RecommendedAdapter adapter = new RecommendedAdapter(response.body().getMovieArrayList(), getApplicationContext(), MovieDetailActivity.this::onItemClick);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                recyclerView.setAdapter(adapter);

                Call<Movie> call2 = retrofitInterface.getMovie(movie.getId() + "", API_KEY, "en-US");

                call2.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        movie = response.body();
                        duration.setText(String.valueOf(movie.getRuntime()) + "min");
                        rating.setText(movie.getRating().toString());
                        company.setText(movie.getCompanyArrayList().get(0).getName());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    public void onItemClick(Context context, Movie movie) {
        Intent goDetail = new Intent(getApplicationContext(), MovieDetailActivity.class);
        goDetail.putExtra("movie", movie);
        startActivity(goDetail);
    }
}
