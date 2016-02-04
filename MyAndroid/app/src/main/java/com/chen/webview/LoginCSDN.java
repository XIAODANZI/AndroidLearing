package com.chen.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nineteen.myandroid.R;

public class LoginCSDN extends FragmentActivity {

	WebView webView;
	String userName = "08254976@163.com";
	String password = "TIANyu860825";

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
		webView.setWebViewClient(new MyWebViewClient());
		webView.loadUrl("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn");
	}

	class MyWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			view.loadUrl("javascript:(function(){"
					+ "var mUserName = document.getElementById(\"username\");"
					+ "var mPassword = document.getElementById(\"password\");"
					+ "mUserName.value = \"" + userName + "\";"
					+ "mPassword.value = \"" + password + "\";" + "})()");
			System.out.println("mUserName.value = \"" + userName + "\";");
		}
	}
}
