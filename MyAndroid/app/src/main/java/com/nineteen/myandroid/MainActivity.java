package com.nineteen.myandroid;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.nineteen.myandroid.adapter.MainAdapter;


public class MainActivity extends Activity {
	
	AutoCompleteTextView completeTextView;
	HashMap<String, String> map = new HashMap<String,String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.listview);
		completeTextView = (AutoCompleteTextView) findViewById(R.id.auto_completedtextview);
		
		String[] titles = getResources().getStringArray(R.array.main_titles);
		String[] main_classes = getResources().getStringArray(R.array.main_classes);
		for(int i=0; i< titles.length && i< main_classes.length; i++) {
			map.put(titles[i], main_classes[i]);
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.only_textview, titles);
		completeTextView.setAdapter(arrayAdapter);
		MainAdapter adapter = new MainAdapter(titles, this);
		listView.setAdapter(adapter);
		// 注册点击事件监听
		listView.setOnItemClickListener(new MyOnItemClickListener());
		completeTextView.setOnItemClickListener(new MyOnItemClickListener());
		completeTextView.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String name = v.getText().toString();
				jump(name);
				return true;
			}
		});
	}

	class MyOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (parent.getAdapter() instanceof MainAdapter) {
				//jumpToOtherActivity(position);
				String toclass = parent.getItemAtPosition(position).toString();
				jump(toclass);
			} else if(parent.getAdapter() instanceof ArrayAdapter) {
				String toclass = parent.getItemAtPosition(position).toString();
				jump(toclass);
			}
		}

	}
	
	public void jump(String name){
		String toclass = map.get(name);
		if(toclass != null && toclass.length() > 0) {
			Class cls;
			try {
				//反射
				cls = Class.forName(toclass);
				Intent intent= new Intent(this, cls);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				Toast.makeText(this, "控件未找到!请重新搜索", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "控件未找到!请重新搜索", Toast.LENGTH_SHORT).show();
		}
	}

	/*// 跳转到其他界面
	public void jumpToOtherActivity(int position) {
		Intent intent = null;
		switch (position) {
		case 0:
			//点击当前的项，跳转到相应的界面
			intent = new Intent(this, TestLinearLayout.class);
			break;
		case 1:
			intent = new Intent(this, TestFrameLayout.class);
			break;
		case 2:
			intent = new Intent(this, TestRelativeLayoutActivity.class);
			break;
		case 3:
			intent = new Intent(this, StepRelativeLayoutActivity.class);
			break;
		case 4:
			intent = new Intent(this, TestTableLayout.class);
			break;
		case 5:
			intent = new Intent(this, TestGridLayout.class);
			break;
		case 6:
			intent = new Intent(this, TestTextViewActivity.class);
			break;
		case 7:
			intent = new Intent(this, TestButtonActivity.class);
			break;
		case 8:
			intent = new Intent(this, TestEditTextActivity.class);
			break;
		case 9:
			intent = new Intent(this, TestImageViewActivity.class);
			break;
		case 10:
			intent = new Intent(this, TestTabActivity.class);
			break;
		case 11:
			intent = new Intent(this, TestRadioCheckActivity.class);
			break;
		case 12: //ProgressBar
			intent = new Intent(this, TestProgressBarActivity.class);
			break;
		case 13://SeekBar
			intent = new Intent(this, TestSeekBarActivity.class);
			break;
		case 14://datepicker & timepicker
			intent = new Intent(this, TestDateTimePickerActivity.class);
			break;
		case 15:
			intent = new Intent(this, TestListViewActivity.class);
			break;
		case 16: //ArrayAdapter
			intent = new Intent(this, TestListViewArrayAdapter.class);
			break;
		case 17: //SimpleAdapter
			intent = new Intent(this, TestListViewSimpleAdapter.class);
			break;
		case 18: //BaseAdapter
			intent = new Intent(this, TestListViewBaseAdapter.class);
			break;
		case 19:
			intent = new Intent(this, TestCarActivity.class);
			break;
		case 20:
			intent = new Intent(this, TestLinearListViewActivity.class);
			break;
		case 21:
			intent = new Intent(this, com.three.androidlearning.adapterview.TestCarActivity.class);
			break;
		case 22: //AutoCompleteTextView
			intent = new Intent(this, TestAuctoCompletedTextView.class);
			break;
		case 23:
			intent = new Intent(this, TestSpinnerActivity.class);
			break;
		}
		if (intent != null) {
			// 跳转到其他界面
			startActivity(intent);
		}
	}*/
}
