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
    private OnItemClickListener mListener; //Pour l'ouverture du détail d'un article en particulier

    // Pour l'affichage Pair et Impair
    private static final int Layout_pair = 0;
    private static final int Layout_impair = 1;

    //Pour l'ouverture du détail d'un article
    public interface OnItemClickListener {
        void onItemClick(int position);  //Création de la méthode de notre interface
    }

    //Cette méthode sera appelée dans le MainActivity
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public RecyclerViewAdapter(Context context, ArrayList<Articles> articlesList) {
        mContext = context;
        mArticlesList = articlesList;
    }


    // Affichage IMPAIR et PAIR grâce à cet méthode : permet de retourner différent view dans onCreateViewHolder
    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0)
            return Layout_pair;
        else
            return Layout_impair;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(mContext).inflate(R.layout.articles_item, parent, false);
        //View impair = LayoutInflater.from(mContext).inflate(R.layout.articles_item_impair, parent, false    );

        View view = null;
        //RecyclerView.ViewHolder viewHolder = null;

        if(viewType == Layout_pair)
            view = LayoutInflater.from(mContext).inflate(R.layout.articles_item, parent, false);
        else
            view = LayoutInflater.from(mContext).inflate(R.layout.articles_item_impair, parent, false);

        return new RecyclerViewHolder(view);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition(); //La position de l'item sera sauvegardé dans cette variable
                        if (position != RecyclerView.NO_POSITION) {  //Vérifie si la position est tjrs valide
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}