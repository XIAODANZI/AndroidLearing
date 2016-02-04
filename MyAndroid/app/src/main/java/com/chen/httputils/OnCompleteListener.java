package com.chen.httputils;

import com.chen.httputils.AsyncHttpClient.HttpClientThread;




public interface OnCompleteListener {
	public void onComplete(HttpClientThread clientThread);
}
