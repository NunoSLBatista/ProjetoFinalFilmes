package com.example.projetofinalfilmes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {


    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccount = findViewById(R.id.createAccount);

    }

    public void createAccountEvent(View view){
        Intent intentCreate = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentCreate);
    }

    public void goBack(View view){
        this.finish();
    }

}
