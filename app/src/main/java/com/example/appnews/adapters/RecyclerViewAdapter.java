package com.example.appnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appnews.R;
import com.example.appnews.model.Articles;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Articles> mData;
    RequestOptions option;   /** Nécessaire pour utiliser Glide ici **/

    public RecyclerViewAdapter(Context mContext, List<Articles> mData) {
        this.mContext = mContext;
        this.mData = mData;

        /** Pour Glide, on utilise le fichier d'affichage destinée à recevoir les images **/
        option = new RequestOptions().centerCrop().placeholder(R.drawable.contenant_forme);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.articles_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /** Pour la partie Texte **/
        holder.artic_name.setText(mData.get(position).getName());
        holder.artic_auteur.setText(mData.get(position).getAuthor());
        holder.artic_date.setText(mData.get(position).getPublishedAt());
        holder.artic_description.setText(mData.get(position).getDescription());

        /** Pour la partie Image **/
        Glide.with(mContext).load(mData.get(position).getUrlToImage()).apply(option).into(holder.artic_img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView artic_img;
        TextView artic_name;
        TextView artic_auteur;
        TextView artic_date;
        TextView artic_description;


        public MyViewHolder(View itemView) {
            super(itemView);

            artic_img = itemView.findViewById(R.id.vignette);
            artic_name = itemView.findViewById(R.id.article_name);
            artic_auteur = itemView.findViewById(R.id.auteur_name);
            artic_date = itemView.findViewById(R.id.publication);
            artic_description = itemView.findViewById(R.id.descriptif);

        }
    }
}
