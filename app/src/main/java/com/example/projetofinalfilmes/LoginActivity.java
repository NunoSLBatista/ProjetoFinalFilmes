package com.example.projetofinalfilmes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.projetofinalfilmes.data.DatabaseHandler;
import com.example.projetofinalfilmes.models.GenreResult;
import com.example.projetofinalfilmes.models.SearchResult;
import com.example.projetofinalfilmes.network.GetDataService;
import com.example.projetofinalfilmes.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    LinearLayout buttonRegister;
    private static String API_KEY = "7c0d1ae5e4fa7e4a562859f06f4c7c3a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonRegister = findViewById(R.id.buttonRegister);

        GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GenreResult> call = retrofitInterface.getGenres("en-US", API_KEY);

        call.enqueue(new Callback<GenreResult>() {
            @Override
            public void onResponse(@NonNull Call<GenreResult> call, @NonNull Response<GenreResult> response) {
                DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
                assert response.body() != null;
                Log.d("Response", response.raw().request().url().toString());
                dh.addGenres(response.body().getGenreArrayList());
            }

            @Override
            public void onFailure(@NonNull Call<GenreResult> call,@NonNull Throwable t) {

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(goRegister);
            }
        });


    }
}
