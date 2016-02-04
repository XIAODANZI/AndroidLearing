package com.chen.webview;

import com.nineteen.myandroid.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestLocalJS extends FragmentActivity implements OnClickListener {

	WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview_button);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("file:///android_asset/html/login.html");

		findViewById(R.id.btn_insert).setOnClickListener(this);

		// webView.addJavascriptInterface(new MyJavaSrciptInterface(this),
		// "t_demo");
		webView.addJavascriptInterface(new MyJavaSrciptInterface(this),
				"d_demo");

	}

	@Override
	public void onClick(View v) {
		webView.loadUrl("javascript:insert('今天7月13号')");
	}

}
