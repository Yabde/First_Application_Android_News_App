package com.example.appnews.adapters;


import android.content.Context;
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
        holder.artic_auteur.setText(creatorAuthor);
        holder.artic_date.setText(creatorDate);
        //holder.artic_description.setText(creatorDescription);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.artic_img);

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






//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
//
//    private Context mContext;
//    private List<Articles> mData;
//    RequestOptions option;   /** Nécessaire pour utiliser Glide ici **/
//
//    public RecyclerViewAdapter(Context mContext, List<Articles> mData) {
//        this.mContext = mContext;
//        this.mData = mData;
//
//        /** Pour Glide, on utilise le fichier d'affichage destinée à recevoir les images **/
//        option = new RequestOptions().centerCrop().placeholder(R.drawable.contenant_forme).error(R.drawable.contenant_forme);
//
//    }
//
//
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//
//        View view;
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        view = inflater.inflate(R.layout.articles_item, parent, false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        /** Pour la partie Texte **/
//        holder.artic_name.setText(mData.get(position).getName());
//        holder.artic_auteur.setText(mData.get(position).getAuthor());
//        holder.artic_date.setText(mData.get(position).getPublishedAt());
//        holder.artic_description.setText(mData.get(position).getDescription());
//
//        /** Pour la partie Image **/
//        Glide.with(mContext).load(mData.get(position).getUrlToImage()).apply(option).into(holder.artic_img);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView artic_name;
//        TextView artic_auteur;
//        TextView artic_date;
//        TextView artic_description;
//        ImageView artic_img;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            artic_name = itemView.findViewById(R.id.article_name);
//            artic_auteur = itemView.findViewById(R.id.auteur_name);
//            artic_date = itemView.findViewById(R.id.publication);
//            artic_description = itemView.findViewById(R.id.descriptif);
//            artic_img = itemView.findViewById(R.id.vignette);
//
//        }
//    }
//}
