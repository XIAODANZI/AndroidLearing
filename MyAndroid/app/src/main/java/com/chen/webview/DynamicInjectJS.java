package com.chen.webview;

import com.nineteen.myandroid.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DynamicInjectJS extends FragmentActivity {

	WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
		webView.setWebViewClient(new MyWebViewClient());
		webView.loadUrl("http://xw.qq.com");

		webView.addJavascriptInterface(new MyJavaSrciptInterface(), "demo");
	}

	class MyWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			view.loadUrl("javascript:(function(){"
					+ "var objs = document.getElementsByTagName(\"img\");"
					+ "for (var i = 0; i < objs.length; i++) {"
					+ " objs[i].onclick = function() { "
					+ " window.demo.jsInvokeJava(this.src);" + " }" + "}"
					+ "})()");
		}
	}

	class MyJavaSrciptInterface {

		@JavascriptInterface
		public void jsInvokeJava(String imgUrl) {
			System.out.println("Click imageUrl = " + imgUrl);
		}
	}

}
