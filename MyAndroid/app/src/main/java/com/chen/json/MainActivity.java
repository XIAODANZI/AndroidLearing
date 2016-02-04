package com.chen.json;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class MainActivity extends FragmentActivity {

	public static final int REQUESTCODE = 110;
	String strJSON = null;
	ListView listView;
	List<MyProjectBean> list = new ArrayList<MyProjectBean>();
	MyProjectAdapter adapter;
	NumberFormat numberFormat = NumberFormat.getNumberInstance();
	{
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = "http://192.168.12.235/outersource/"
				+ "action.php?paramaters=kkxGgRdJ%2Bx5foTrp5m3xNxzHdhVo1BnaSMtAXIUlPP57"
				+ "HyG14F1Ju2PygpLsS9tzCEW01rqVITRoAmQwGkInHPL3Gm4nJJjRRDPVWFhKYaE%3D";
		Intent intent = new Intent(this, GetJSONString.class);
		intent.putExtra("url", url);
		startActivityForResult(intent, REQUESTCODE);
		setContentView(R.layout.parsejson);
		listView = (ListView) findViewById(R.id.listview_json);
		adapter = new MyProjectAdapter(this, list);
		listView.setAdapter(adapter);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUESTCODE && resultCode == RESULT_OK
				&& data != null && data.hasExtra("strJSON")) {
			strJSON = data.getStringExtra("strJSON");
			// System.out.println(strJSON);
			addDateSrc();
		}
	}

	public List<MyProjectBean> praseStrJSON() {
		List<MyProjectBean> list = new ArrayList<MyProjectBean>();
		try {

			JSONObject jsonObject = new JSONObject(strJSON);
			JSONArray jsonArray = jsonObject.optJSONArray("list");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.optJSONObject(i);
				MyProjectBean bean = new MyProjectBean();
				bean.pro_id = object.optInt("pro_id");
				bean.user_id = object.optInt("user_id");
				bean.pro_category_id = object.optInt("pro_category_id");
				bean.pro_name = object.optString("pro_name");
				bean.pro_price = numberFormat
						.format(object.optInt("pro_price"));
				bean.pro_desc = object.optString("pro_desc");
				bean.pro_start_time = object.optString("pro_start_time");
				bean.pro_end_time = object.optString("pro_end_time");
				bean.pro_directory = object.optString("pro_directory");
				bean.pro_date = object.optString("pro_date").substring(0, 10);
				bean.pro_gress = object.optInt("pro_gress");
				bean.pro_close = object.optInt("pro_close");
				list.add(bean);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addDateSrc() {
		list.addAll(praseStrJSON());
		adapter.notifyDataSetChanged();
	}
}
