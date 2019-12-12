package com.example.appnews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnews.adapters.RecyclerViewAdapter;
import com.example.appnews.model.Articles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //AlertDialog.Builder pas_de_connexion;

    private final String Json_source_url = "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=lequipe";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Articles> listeArticles;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        pas_de_connexion = new AlertDialog.Builder(this);
        pas_de_connexion.setTitle("Erreur de connexion");
        pas_de_connexion.setMessage("Connectez vous à internet");  **/


        listeArticles = new ArrayList<>();  //Servira pour tout stocker
        recyclerView = findViewById(R.id.recycler_view_id);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        jsonrequest();

        /**
        ArrayList<Articles> articles = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new ); **/

    }


    /** On définie notre requete pour récupérer les infos souhaitées **/
    private void jsonrequest() {
        Log.d("coucou", "launch request");
        request = new JsonArrayRequest(Json_source_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                Log.d("coucou", "request done");

                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Articles articles = new Articles();
                        articles.setName(jsonObject.getString("title"));
                        articles.setAuthor(jsonObject.getString("author"));
                        articles.setPublishedAt(jsonObject.getString("publishedAt"));
                        articles.setDescription(jsonObject.getString("description"));
                        articles.setUrlToImage(jsonObject.getString("urlToImage"));
                        listeArticles.add(articles);
                        Log.d("coucou", "tesy");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    setuprecyclerview(listeArticles);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("coucou", "request failed"+error.getMessage());
            }
        });


        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Articles> listeArticles) {
        RecyclerViewAdapter my_adapter = new RecyclerViewAdapter(this, listeArticles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(my_adapter);
    }
}
