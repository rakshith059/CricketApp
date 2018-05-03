package com.rakshith.cricketapp.cricketAdmin.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rakshith.cricketapp.R;

public class WebViewActivity extends AppCompatActivity {

    public static final String TAG = WebViewActivity.class.getSimpleName();
    public static final String EXTRA_INTENT_PAGE_TITLE = TAG + ".pageTitle";
    public static final String EXTRA_INTENT_PAGE_URL = TAG + ".pageUrl";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.webview_toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = getResources().getString(R.string.privacy_policy);
        String url = "https://raw.githubusercontent.com/rakshith059/CricketApp/master/privacy_policy";
        setUpWebView(title, url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpWebView(String pageTitle, String url) {
        final WebView webView = (WebView) findViewById(R.id.web_view);
        final View loadPanel = findViewById(R.id.loading_panel);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @SuppressLint("LongLogTag")
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loadPanel.setVisibility(View.VISIBLE);
                Log.d("On Page started loading %s", url);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onPageFinished(WebView view, String url) {
                loadPanel.setVisibility(View.GONE);
                Log.d("On Page finished loading %s", url);
                webView.getSettings().setJavaScriptEnabled(false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("Should override url %s", url);
                return true;
            }
        });
        getSupportActionBar().setTitle(pageTitle);
        webView.loadUrl(url);
    }
}