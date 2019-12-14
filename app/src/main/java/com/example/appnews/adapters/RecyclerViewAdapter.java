package com.example.appnews.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.appnews.R;
import com.example.appnews.model.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<Articles> mArticlesList;

    public RecyclerViewAdapter(Context context, ArrayList<Articles> articlesList) {
        mContext = context;
        mArticlesList = articlesList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.articles_item, parent, false);

        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        //Pour accéder à la position sans avoir à tout reécrire...
        Articles currentArticles = mArticlesList.get(position);

        String imageUrl = currentArticles.getUrlToImage();
        String creatorName = currentArticles.getTitle();
        String creatorAuthor = currentArticles.getAuthor();
        String creatorDate = currentArticles.getPublishedAt();
        //String creatorDescription = currentArticles.getDescription();

        holder.artic_name.setText(creatorName);
        holder.artic_date.setText(creatorDate);
        //holder.artic_description.setText(creatorDescription);

        // Prise en compte des Auteurs Manquants
        if (creatorAuthor.equals("null")) {
            holder.artic_auteur.setText("Pas d'auteur.");
        }
        else {
            holder.artic_auteur.setText(creatorAuthor);
        }

        // Prise en compte des Images Manquantes
        if (imageUrl.equals("null")) {
            Picasso.with(mContext).load(R.drawable.pas_dimage_disponible).fit().centerInside().into(holder.artic_img);
            Log.d("moumou", String.valueOf(holder.artic_img));
        }
        else {
            Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.artic_img);
            Log.d("blabla", String.valueOf(holder.artic_img));
        }
        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.artic_img);
    }

    @Override
    public int getItemCount() {
        return mArticlesList.size();  // Pour avoir autant d'item que notre adapter en aura dans son ArrayList...
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView artic_name;
        public TextView artic_auteur;
        public TextView artic_date;
        public TextView artic_description;
        public ImageView artic_img;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            artic_name = itemView.findViewById(R.id.article_name);
            artic_auteur = itemView.findViewById(R.id.auteur_name);
            artic_date = itemView.findViewById(R.id.publication);
            //artic_description = itemView.findViewById(R.id.descriptif);
            artic_img = itemView.findViewById(R.id.vignette);

        }
    }

}