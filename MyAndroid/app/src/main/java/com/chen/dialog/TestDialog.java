package com.chen.dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.chen.adapterview.MyBaseAdapter;
import com.chen.adapterview.Mybean;
import com.chen.dialog.CustomAlertDialog.Builder;
import com.nineteen.myandroid.R;

public class TestDialog extends Activity implements OnClickListener {

	Button[] btns = new Button[12];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_dialog);
		btns[0] = (Button) findViewById(R.id.show_dialog);
		btns[1] = (Button) findViewById(R.id.show_activity_dialog);
		btns[2] = (Button) findViewById(R.id.show_adapter_dialog);
		btns[3] = (Button) findViewById(R.id.show_custom_dialog);
		btns[4] = (Button) findViewById(R.id.show_custom_style_dialog);
		btns[5] = (Button) findViewById(R.id.show_custom_theme_dialog);
		btns[6] = (Button) findViewById(R.id.show_datepicker_dialog);
		btns[7] = (Button) findViewById(R.id.show_multi_dialog);
		btns[8] = (Button) findViewById(R.id.show_my_dialog);
		btns[9] = (Button) findViewById(R.id.show_progress_dialog);
		btns[10] = (Button) findViewById(R.id.show_single_dialog);
		btns[11] = (Button) findViewById(R.id.show_timepicker_dialog);
		for (int i = 0; i < btns.length; i++) {
			btns[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.show_dialog:
			showDialog();
			break;
		case R.id.show_activity_dialog:
			Intent intent = new Intent(this, ActivityAsDialog.class);
			startActivity(intent);
			break;
		case R.id.show_adapter_dialog:
			showAdapterDialog();
			break;
		case R.id.show_custom_dialog:
			showCustomViewDialog();
			break;
		case R.id.show_custom_style_dialog:
			showCustomStyleDialog();
			break;
		case R.id.show_custom_theme_dialog:
			showCustomThemeDialog();
			break;
		case R.id.show_datepicker_dialog:
			showDatepickerDialog();
			break;
		case R.id.show_my_dialog:
			showMyDialog();
			break;
		case R.id.show_progress_dialog:
			showProgressDialog();
			break;
		case R.id.show_single_dialog:
			showSingleDialog();
			break;
		case R.id.show_timepicker_dialog:
			showTimepickerDialog();
			break;
		case R.id.show_multi_dialog:
			showMultiDialog();
			break;

		default:
			break;
		}

	}

	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.blue);
		builder.setTitle("吃否？");
		builder.setMessage("吃了吗？");
		/**
		 * 下方三个按钮的监听事件
		 */
		DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case AlertDialog.BUTTON_NEGATIVE:
					System.out.println("BUTTON_NEGATIVE");
					break;
				case AlertDialog.BUTTON_NEUTRAL:
					System.out.println("BUTTON_NEUTRAL");
					break;
				case AlertDialog.BUTTON_POSITIVE:
					System.out.println("BUTTON_POSITIVE");
					break;

				default:
					break;
				}
			}

		};
		builder.setPositiveButton("吃了", listener);
		builder.setNeutralButton("不知道", listener);
		builder.setNegativeButton("没吃", listener);
		/**
		 * 返回键等的监听事件
		 */
		builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
			/**
			 * return false 会出触发其他事件 ；return true 不会触发其他事件
			 */
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {

				switch (keyCode) {
				// 返回键
				case KeyEvent.KEYCODE_BACK:
					// 对话框消失
					dialog.dismiss();
					System.out.println("KEYCODE_BACK");
					break;
				default:
					break;
				}
				return false;
			}
		});
		builder.create();
		builder.show();
	}

	public void showCustomViewDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.blue);
		builder.setTitle(R.string.show_custom_dialog);

		// 加载自定义的布局
		View view = LayoutInflater.from(this).inflate(
				R.layout.test_linearlayout, null);
		// 绑定自定义布局中的控件并设置监听事件
		Button btn = (Button) view.findViewById(R.id.btn_0);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(TestDialog.this, "点击了数字0", Toast.LENGTH_SHORT)
						.show();
			}
		});
		// 不监听事件时，参数为null
		builder.setPositiveButton("确定", null);
		builder.setView(view);
		builder.create().show();
	}

	public void showSingleDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.blue);
		builder.setTitle("性别：");
		String[] genders = { "男", "女" };
		final boolean[] checkedItems = { true, false };
		builder.setSingleChoiceItems(genders, 0,
				new DialogInterface.OnClickListener() {

					/**
					 * which是items的索引
					 */
					@Override
					public void onClick(DialogInterface dialog, int which) {

						switch (which) {
						case 0:
							checkedItems[0] = true;
							checkedItems[1] = false;
							break;
						case 1:
							checkedItems[1] = true;
							checkedItems[0] = false;
							break;
						}
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				System.out.println(checkedItems[0] + " " + checkedItems[1]);
			}
		});
		builder.create().show();

	}

	public void showMultiDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.blue);
		builder.setTitle("爱好：");
		String[] hobby = { "睡觉", "看书", "逛街" };
		final boolean[] checkedItems = { false, false, false };
		builder.setMultiChoiceItems(hobby, checkedItems,
				new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						checkedItems[which] = isChecked;

					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				System.out.println(checkedItems[0] + " " + checkedItems[1]
						+ " " + checkedItems[2]);
			}
		});
		builder.create().show();
	}

	public void showAdapterDialog() {

		int[] images = { R.drawable.bird_1, R.drawable.bird_2,
				R.drawable.bird_3, R.drawable.bird_4, R.drawable.bird_5,
				R.drawable.bird_7 };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();
		dialog.setIcon(R.drawable.blue);
		dialog.setTitle(R.string.show_adapter_dialog);
		// 加载布局
		View view = LayoutInflater.from(this).inflate(R.layout.test_gridview,
				null);
		GridView gridView = (GridView) view.findViewById(R.id.gridView);
		List<Mybean> list = new ArrayList<Mybean>();
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = "图标";
			myBean.classes = null;
			list.add(myBean);
		}
		MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, list);
		gridView.setAdapter(myBaseAdapter);
		// 给对话框设置view
		dialog.setView(view);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 点击任一item后消失
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	Calendar calendar = Calendar.getInstance(Locale.CHINA);

	public void showTimepickerDialog() {
		TimePickerDialog timePickerDialog = new TimePickerDialog(this,
				new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
						calendar.set(Calendar.MINUTE, minute);
					}
				}, calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true);
		timePickerDialog.setTitle("请选择时间");
		timePickerDialog.show();

	}

	public void showDatepickerDialog() {
		DatePickerDialog datePickerDialog = new DatePickerDialog(this,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						calendar.set(year, monthOfYear, dayOfMonth);
					}
				}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.setTitle("请选择时间");
		datePickerDialog.show();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 123:
				progressDialog.incrementProgressBy(2);
				break;
			case 124:
				progressDialog.dismiss();
				break;
			}
			return true;
		}
	});

	ProgressDialog progressDialog;

	public void showProgressDialog() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("正在进行中……");
		// 水平样式
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.setProgress(0);
		progressDialog.show();
		new Thread() {
			public void run() {
				while (progressDialog.getProgress() < 100) {
					handler.sendEmptyMessage(123);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(124);
			};
		}.start();
	}

	public void showMyDialog() {
		final MyDialog myDialog = new MyDialog(this);
		myDialog.setTitle("自定义Dialog");
		myDialog.setIcon(R.drawable.blue);
		myDialog.setMessage("自定义Dialog");
		myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestDialog.this, "点完消失", Toast.LENGTH_SHORT)
						.show();
				myDialog.dismiss();
			}
		});
		myDialog.show();
	}

	public void showCustomThemeDialog() {
		final CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(
				this);
		builder.setTitle("自定义AlertDialog");
		builder.setIcon(R.drawable.blue);
		builder.setMessage("自定义AlertDialog");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestDialog.this, "点完消失", Toast.LENGTH_SHORT)
						.show();
				builder.create().dismiss();
			}
		});
		builder.show();
	}

	public void showCustomStyleDialog() {
		final CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(
				new ContextThemeWrapper(this,
						android.R.style.Theme_DeviceDefault_Light_Dialog));
		builder.setTitle("自定义Theme的AlertDialog");
		builder.setIcon(R.drawable.blue);
		builder.setMessage("自定义Theme的AlertDialog");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestDialog.this, "点完消失", Toast.LENGTH_SHORT)
						.show();
				builder.create().dismiss();
			}
		});
		builder.show();
	}

}
