package com.chen.test.textview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.nineteen.myandroid.R;

public class Test_RadioGroup_CheckBox extends Activity implements
		OnClickListener {

	RadioGroup radioGroup;
	CheckBox[] checkBoxs = new CheckBox[3];

	boolean checks[] = new boolean[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.test_radiongroup_checkbutton);

		// 绑定控件
		// 重载按钮
		findViewById(R.id.btnReset).setOnClickListener(this);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rbtn_0:
					Log.d("nineteen", "male");
					break;
				case R.id.rbtn_1:
					Log.d("nineteen", "female");

					break;

				default:
					break;
				}
			}
		});

		// 绑定复选框，并设置监听事件
		for (int i = 0; i < checkBoxs.length; i++) {

			checkBoxs[i] = (CheckBox) findViewById(R.id.checkbox_0 + i);
			checkBoxs[i]
					.setOnCheckedChangeListener(new MyCheckedChangeListener());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnReset:
			// 清空radioGroup的选项
			radioGroup.clearCheck();
			RadioButton rb = (RadioButton) findViewById(R.id.rbtn_0);
			rb.setChecked(true);
			for (int i = 0; i < checkBoxs.length; i++) {
				checkBoxs[i].setChecked(false);
			}
			break;

		default:
			break;
		}
	}

	class MyCheckedChangeListener implements
			android.widget.CompoundButton.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

			switch (buttonView.getId()) {
			case R.id.checkbox_0:
				checks[0] = isChecked;

				break;
			case R.id.checkbox_1:
				checks[1] = isChecked;

				break;
			case R.id.checkbox_2:
				checks[2] = isChecked;

				break;

			default:
				break;
			}

			String strChecks = "";
			for (int i = 0; i < checks.length; i++) {
				strChecks += checks[i] + ",";
			}
			Log.d("nineteen", strChecks);

		}

	}

}
