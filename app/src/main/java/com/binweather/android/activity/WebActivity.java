package com.binweather.android.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.binweather.android.R;

public class WebActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView web_title;
    private Button web_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web_title=(TextView)findViewById(R.id.web_title);
        web_back=(Button)findViewById(R.id.web_back);
        WebView webView=(WebView)findViewById(R.id.web_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent=getIntent();
        String url=intent.getStringExtra("URL");
        String title=intent.getStringExtra("TITLE");
        web_title.setText(title);
        if (url!=null) {
            webView.loadUrl(url);
        }
        web_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.web_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
