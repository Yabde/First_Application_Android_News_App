package com.example.appnews;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Appel de l'activité principal depuis notre Splashcreen

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
