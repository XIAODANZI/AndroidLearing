package com.chen.fifth.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.dialog.CustomAlertDialog;
import com.nineteen.myandroid.R;

public class EditLogActivity extends FragmentActivity implements
		OnClickListener, TextWatcher {

	EditText title, content;
	Button send, cancel;
	TextView modifyLog;
	LogBean bean;
	int type = 0;// 0是添加新日志；1是修改日志
	boolean isModified = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifth_write_log);
		title = (EditText) findViewById(R.id.edit_title);
		modifyLog = (TextView) findViewById(R.id.textview_log);
		content = (EditText) findViewById(R.id.edit_content);
		send = (Button) findViewById(R.id.btn_send);
		cancel = (Button) findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(this);
		send.setOnClickListener(this);
		if (getIntent() != null && getIntent().hasExtra("LogBean")) {
			bean = (LogBean) getIntent().getSerializableExtra("LogBean");
		}
		if (bean != null) {
			type = 1;
			title.setText(bean.title);
			content.setText(bean.content);
			modifyLog.setText("修改日志");
			send.setText("修改日志");
		}

		title.addTextChangedListener(this);
		content.addTextChangedListener(this);

	}

	@Override
	public void afterTextChanged(Editable s) {
		isModified = true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_send:
			if (type == 1) {
				update();
			} else {
				insertLog();
			}
			break;
		case R.id.btn_cancel:
			if (isModified) {
				hint("提示", "此次修改内容不会保存，请确认", 1);
			} else {
				finish();
			}
			break;
		}
	}

	private void insertLog() {

		// 得到日志的创建时间
		String strDate = getCurDate();

		String strTitle = title.getText().toString().trim();
		String strContent = content.getText().toString().trim() == null ? ""
				: content.getText().toString().trim();

		if (strTitle != null && !strTitle.equals("")) {
			ContentValues values = new ContentValues();
			values.put("title", strTitle);
			values.put("content", strContent);
			values.put("date", strDate);
			Uri insertUri = getContentResolver().insert(
					LogContentProvider.LOG_URI, values);
			if (insertUri != null) {
				Toast.makeText(this, "新建日志成功", Toast.LENGTH_SHORT).show();
				finish();
			}
		} else {
			hint("错误", "日志标题不能为空", 0);
		}
	}

	private void update() {

		String strDate = getCurDate();

		String strTitle = title.getText().toString().trim();
		String strContent = content.getText().toString().trim() == null ? ""
				: content.getText().toString().trim();

		if (strTitle != null && !strTitle.equals("")) {
			ContentValues values = new ContentValues();
			values.put("title", strTitle);
			values.put("content", strContent);
			values.put("date", strDate);
			long count = getContentResolver().update(
					LogContentProvider.LOG_URI, values, "id = ?",
					new String[] { bean.id + "" });
			if (count > 0) {
				Toast.makeText(this, "修改日志成功", Toast.LENGTH_SHORT).show();
				finish();
			}
		} else {
			hint("错误", "日志标题不能为空", 0);
		}
	}

	public void hint(String dialogTitle, String dialogMsg, final int from) {
		final CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(
				new ContextThemeWrapper(this,
						android.R.style.Theme_DeviceDefault_Light_Dialog));
		builder.setTitle(dialogTitle);
		builder.setIcon(R.drawable.a007);
		builder.setMessage(dialogMsg);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder.create().dismiss();
				if (from == 1) {
					finish();
				}
			}
		});
		if (from == 1) {
			// 修改日志或新建日志
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							builder.create().dismiss();
						}
					});
		}
		builder.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isModified) {
				hint("提示", "此次修改内容不会保存，请确认", 1);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@SuppressLint("SimpleDateFormat")
	public String getCurDate() {
		// 得到日志的创建时间
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(date);
		return strDate;
	}

}
