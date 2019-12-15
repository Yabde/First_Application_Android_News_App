package com.example.appnews;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = "https://www.google.fr";

        webView = (WebView) findViewById(R.id.page_webview);
        webView.setWebViewClient(new WebViewClient());  //Permet d'afficher dans notre app et pas dans le navigateur

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
        }

        webView.loadUrl(url);

        //Facultatif si on veut rajouter des options...
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);   //Utile car Permet de fermer les Popups de Cookie pour Le Monde Par exemple...
    }

    //On va permettre un retour en arrière sans fermer l'application
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
