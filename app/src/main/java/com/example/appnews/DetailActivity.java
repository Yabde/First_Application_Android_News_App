package com.example.appnews;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import static com.example.appnews.MainActivity.Extra_author;
import static com.example.appnews.MainActivity.Extra_date;
import static com.example.appnews.MainActivity.Extra_description;
import static com.example.appnews.MainActivity.Extra_img_url;
import static com.example.appnews.MainActivity.Extra_source_name;
import static com.example.appnews.MainActivity.Extra_title;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String create_title = intent.getStringExtra(Extra_title);
        String create_author = intent.getStringExtra(Extra_author);
        String create_id = intent.getStringExtra(Extra_source_name);
        String create_description = intent.getStringExtra(Extra_description);
        String create_date = intent.getStringExtra(Extra_date);
        String imageUrl = intent.getStringExtra(Extra_img_url);
        //String create_webview = intent.getStringExtra(Extra_buton_webview);

        TextView textViewTitle = findViewById(R.id.text_view_title_detail);
        TextView textViewAuthor = findViewById(R.id.text_view_author_detail);
        TextView textViewId = findViewById(R.id.text_view_source_detail);
        TextView textViewDescription = findViewById(R.id.text_view_description_detail);
        TextView textViewDate = findViewById(R.id.text_view_date_detail);
        ImageView imageView = findViewById(R.id.image_view_detail);

        textViewTitle.setText(create_title);
        textViewId.setText(create_id);
        textViewDescription.setText(Html.fromHtml("<b>Description : </b>" + create_description));
        textViewDate.setText(create_date);

        // Prise en compte des Auteurs Manquants
        if (create_author.equals("null")) textViewAuthor.setText("Pas d'auteur.");
        else textViewAuthor.setText(Html.fromHtml("<i><u>Auteur</u></i> : " + create_author));

        // Prise en compte des Images Manquantes
        if (imageUrl.equals("null"))
            Picasso.with(this).load(R.drawable.pas_dimage_disponible).fit().centerInside().into(imageView);
        else Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);

    }
}
