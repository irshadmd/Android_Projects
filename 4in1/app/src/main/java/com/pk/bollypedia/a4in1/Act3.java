package com.pk.bollypedia.a4in1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Act3 extends AppCompatActivity {

    private static final int ZOOM_LEVEL = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3);

    WebView webview= (WebView) findViewById(R.id.webview2);
        webview.loadUrl("https://cricbuzz.com");
        webview.setWebViewClient(new WebViewClient());
    WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setInitialScale(ZOOM_LEVEL);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

}
    @Override
    public void onBackPressed()
    {
        WebView webView = (WebView) findViewById(R.id.webview2);
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }}}

