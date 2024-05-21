package com.example.tareasprofcaei;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaWeb extends AppCompatActivity {
    private WebView myWebHTML;
    private WebSettings settingsHTML;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_web);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        myWebHTML =findViewById(R.id.web);

        settingsHTML = myWebHTML.getSettings();
        settingsHTML.setJavaScriptEnabled(true);
        settingsHTML.setDomStorageEnabled(true);

        myWebHTML.loadUrl("https://tareasprofcaei.antojitosjhony.site/view_tasks.php");
        myWebHTML.setWebViewClient(new WebViewClient());
    }
}