package com.example.appnews;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder pas_de_connexion;

    String source = "https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        pas_de_connexion = new AlertDialog.Builder(this);
        pas_de_connexion.setTitle("Erreur de connexion");
        pas_de_connexion.setMessage("Connectez vous à internet");  **/

    }
}
