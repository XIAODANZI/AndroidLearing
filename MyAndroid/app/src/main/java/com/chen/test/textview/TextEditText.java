package com.chen.test.textview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nineteen.myandroid.R;

public class TextEditText extends Activity implements OnClickListener {

	EditText edUserName;
	EditText edPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_edittext);
		Button btn = (Button) findViewById(R.id.login);
		edUserName = (EditText) findViewById(R.id.username);
		edPassword = (EditText) findViewById(R.id.password);
		btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			if (isCheck()) {
				if (login(edUserName.getText().toString().trim(), edPassword
						.getText().toString().trim()))
					Toast.makeText(TextEditText.this, "对了也没用",
							Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(TextEditText.this, "用户名或密码错误",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(TextEditText.this, "用户名或密码错误",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	public boolean isCheck() {
		return true;
	}

	public boolean login(String userName, String password) {
		if (userName.equals("username") && password.equals("123456"))
			return true;
		return false;
	}
}
