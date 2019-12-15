package com.example.appnews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnews.model.Source;
import com.example.appnews.model.SourceList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    String url_source = "https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr";
    private ArrayList<Source> mSourcesList;

    AlertDialog.Builder Pas_Internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSourcesList = new ArrayList<>(); //Pour mettre nos données

        Pas_Internet = new AlertDialog.Builder(this);
        Pas_Internet.setTitle("Alert");
        Pas_Internet.setMessage("Pas de connexion réseau OU bien Requêtes Max atteintes : 250 toutes les 12H...");
        Pas_Internet.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                parseSOURCE_JSON();
            }
        });
        Pas_Internet.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        Pas_Internet.setNeutralButton("Rester quand même", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        parseSOURCE_JSON();
    }

    private void parseSOURCE_JSON() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest request = new JsonObjectRequest(url_source, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SourceList source_list = SourceList.getInstance();
                        source_list.clearSources();

                        try {
                            JSONArray jsonArray = response.getJSONArray("sources");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject sources = jsonArray.getJSONObject(i);

                                String creatorId = sources.getString("id");
                                String creatorName = sources.getString("name");
                                String creatorUrl = sources.getString("url");

                                mSourcesList.add(new Source(creatorId, creatorName, creatorUrl));
                                source_list.addSource(mSourcesList.get(i));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);

                        startActivity(intent);
                        finish();

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Pas_Internet.show();

            }
        });

        mRequestQueue.add(request);
    }

}
