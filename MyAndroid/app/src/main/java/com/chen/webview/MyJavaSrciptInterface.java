package com.chen.webview;

import com.nineteen.myandroid.R;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyJavaSrciptInterface {

	Context context;

	public MyJavaSrciptInterface(Context context) {
		this.context = context;
	}

	@JavascriptInterface
	public void showToast(String toastText) {
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}

	// android和js通信，必须要使用@JavascriptInterface注解该方法。
	@JavascriptInterface
	public void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage(msg);
		builder.setIcon(R.drawable.a005);
		builder.setPositiveButton("确定", null);
		builder.create().show();
	}
	
	@JavascriptInterface
	public void showErrMsg(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage(msg);
		builder.setIcon(R.drawable.a005);
		builder.setPositiveButton("确定", null);
		builder.create().show();
	}

}
