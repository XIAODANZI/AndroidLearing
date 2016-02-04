package com.chen.webview;

import java.io.IOException;
import java.io.InputStream;

import com.nineteen.myandroid.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestWebHtml extends FragmentActivity {

	WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new MyWebViewClient());// 在当前页面打开网页
		webView.getSettings().setJavaScriptEnabled(true); // 设置javaScript可用
		webView.loadUrl("http://192.168.12.235/MyWeb/html");
	}

	class MyWebViewClient extends WebViewClient {

		// 当开始加载的时候调用。
		// view WebView的对象；
		// url 需要加载的URL对象；
		// favicon 标题栏的logo
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			System.out.println("onPageStarted url=" + url);
		}

		@Override
		public WebResourceResponse shouldInterceptRequest(WebView view,
				String url) {
			// return super.shouldInterceptRequest(view, url);
			if (url.endsWith(".png") || url.endsWith(".jpg")
					|| url.endsWith(".gif")) {
				InputStream inputStream = null;
				try {
					inputStream = getAssets().open("images/a010.jpg");
				} catch (IOException e) {
					// TODO: handle exception
				}

				WebResourceResponse response = new WebResourceResponse(
						"image/*", "utf-8", null);
				return response;
			} else {
				return super.shouldInterceptRequest(view, url);
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onLoadResource(WebView view, String url) {
			super.onLoadResource(view, url);
		}

		@Override
		@Deprecated
		public void onTooManyRedirects(WebView view, Message cancelMsg,
				Message continueMsg) {
			super.onTooManyRedirects(view, cancelMsg, continueMsg);
		}

		@Override
		public void onReceivedHttpAuthRequest(WebView view,
				HttpAuthHandler handler, String host, String realm) {
			super.onReceivedHttpAuthRequest(view, handler, host, realm);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
		}
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
