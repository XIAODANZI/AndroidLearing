package com.chen.webview;

import com.nineteen.myandroid.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestLocalHtml extends FragmentActivity {

	WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());// 在当前页面打开网页
		webView.getSettings().setJavaScriptEnabled(true); // 设置javaScript可用
		webView.loadUrl("file:///android_asset/html/abc.html");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下返回键，则webView还可以回退，就回退上一网页
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true; // 事件处理完成
		}
		return super.onKeyDown(keyCode, event);
	}

}
