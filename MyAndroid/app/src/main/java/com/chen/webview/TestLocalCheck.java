package com.chen.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nineteen.myandroid.R;

public class TestLocalCheck extends FragmentActivity {

	WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("file:///android_asset/html/index.html");

		webView.addJavascriptInterface(new MyJavaSrciptInterface(this), "demo");
	}

}
