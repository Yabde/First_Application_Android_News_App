package com.example.appnews;

import android.content.Intent;
import android.os.Bundle;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSourcesList = new ArrayList<>(); //Pour mettre nos données
        parseSOURCE_JSON();

        //Appel de l'activité principal depuis notre Splashcreen
//        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//        startActivity(intent);
//        finish();

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


//                                Source source_provi = new Source();
//                                source_provi.setId(sources.getString("id"));
//                                source_provi.setName(sources.getString("name"));
//                                source_provi.setUrl(sources.getString("url"));
//
//                                source_list.addSource(source_provi);

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

            }
        });

        mRequestQueue.add(request);
    }

}
