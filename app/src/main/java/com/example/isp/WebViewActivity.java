package com.example.isp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView webView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webviewFtp);


        String html ="http://103.91.144.230";

      //  webSettings.setJavaScriptEnabled(true);
        String Url = "<html> <head> <style type=text/css> iframe {height:100%;width:100%;margin:0;padding:0;overflow:scroll;} body {background-color:#000; margin:0;}</style> </head> <body> <iframe width=240px height=220px src="
                + html
                + " frameborder=0 webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe></body></html>";

    //    webView.setLightTouchEnabled(true);



        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.canGoBack();
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true) ;

/*
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDatabaseEnabled(true) ;
 */
        webView.loadDataWithBaseURL(null, Url, "text/html", "utf-8", null);

        webView.loadUrl(Url);




    }
}
