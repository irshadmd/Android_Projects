package com.pk.bollypedia.a4in1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Act5 extends AppCompatActivity {

ProgressBar progressBar;
int progress=0;
    private static final int ZOOM_LEVEL = 100;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_act3);

            progressBar=(ProgressBar) findViewById(R.id.pb1);

            WebView webview= (WebView) findViewById(R.id.webview2);
            webview.loadUrl("https://w3newspapers.com/india/");
            webview.setWebViewClient(new WebViewClient());
            WebSettings webSettings=webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.setInitialScale(ZOOM_LEVEL);
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        }
    @Override
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.webview2);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }}

