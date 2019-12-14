package com.example.appnews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnews.adapters.RecyclerViewAdapter;
import com.example.appnews.model.Articles;
import com.example.appnews.model.Source;
import com.example.appnews.model.SourceList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //AlertDialog.Builder pas_de_connexion;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mArticlesAdapter;
    private ArrayList<Articles> mArticlesList;
    private RequestQueue mRequestQueue;   //Pour Volley

    private SourceList source_list;
    private Source url_source;  // A mettre a la fin de l'url (pour le spinner)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Partie SPINNER **/
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<CharSequence> toutes_les_sources = new ArrayList<>();  //pour tout stocker
        source_list = SourceList.getInstance();

        for(int i = 0; i < source_list.size(); i++){
            toutes_les_sources.add(source_list.get(i).getName());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, toutes_les_sources);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                url_source = source_list.get(position);

                mArticlesList.clear();  //Pour réinitialiser et afficher les nouveaux quand on clique
                parseJSON();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(url_source == null){
            url_source = source_list.get(0);
        }

        /* Usage du Recycler **/

        //D'abord on définit notre recycler view
        mRecyclerView = findViewById(R.id.recycler_view_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArticlesList = new ArrayList<>(); //Pour mettre nos données

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON(); //nom arbitraire : methodes à créer séparément pour pouvoir mettre les données
    }

    private void parseJSON() {

        String url_article = "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=";  //Spécifité à rajouter à la fin de cette adresse selon la source


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_article+url_source.getId(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //on créer nos donnée ici selon la source JSON

                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject articles = jsonArray.getJSONObject(i);

                                String creatorAuthor = articles.getString("author");
                                String creatorName = articles.getString("title");
                                String imageUrl = articles.getString("urlToImage");
                                String creatorDate = articles.getString("publishedAt");
                                //String creatorDescription = articles.getString("description");
                                String article_url = articles.getString("url");

                                Log.d("coucou", articles.getString("urlToImage"));

                                mArticlesList.add(new Articles(creatorAuthor, creatorName, imageUrl, creatorDate, article_url));
                                mArticlesList.get(i).setSource(url_source);
                                //articles.setSource(url_source);
                            }

                            mArticlesAdapter = new RecyclerViewAdapter(MainActivity.this, mArticlesList);
                            mRecyclerView.setAdapter(mArticlesAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("coucou", "request failed"+error.getMessage());
                error.printStackTrace();
            }
        });

        //On doit rajouter notre requete à notre RequestQue
        mRequestQueue.add(request);
    }
}
