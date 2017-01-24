package com.example.prog_abdallah.topmoves;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class HomePageActivity extends AppCompatActivity {

    public static final String TAG = MoviesActivity.class.getName();
    Intent intent;
    WebView webView;
    Dialog progressDialog;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        intent = this.getIntent();

        Log.i(TAG, "onCreate: " + intent.getStringExtra("homePage"));

        url = intent.getStringExtra("homePage");

        if (url.equals("")) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        } else {
            webView = (WebView) findViewById(R.id.web_page_view);

            progressDialog = new Dialog(HomePageActivity.this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.progress_bar);
            progressDialog.setCancelable(false);
            if (CheckInternet.isOnline(getApplicationContext())) {
                try {
                    loadURL();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(HomePageActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }

    private void loadURL() throws UnsupportedEncodingException {

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }
        });

        webView.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            else
                webView.goBack();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
