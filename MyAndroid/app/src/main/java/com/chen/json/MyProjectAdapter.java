package com.chen.json;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class MyProjectAdapter extends BaseAdapter {

	class Holder {
		TextView pro_name;
		TextView pro_price;
		TextView pro_date;
	}

	List<MyProjectBean> list = new ArrayList<MyProjectBean>();
	LayoutInflater inflater;

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param list
	 *            数据源
	 */
	public MyProjectAdapter(Context context, List<MyProjectBean> list) {
		// Log.d("nineteen", "MyBaseAdapter");
		inflater = LayoutInflater.from(context); // 获取到布局加载器对象
		this.list = list;
	}

	public void setDataChange(List<MyProjectBean> list) {
		this.list = list;
	}

	/**
	 * 返回当前列表中数据项的总数
	 */
	@Override
	public int getCount() {

		// Log.d("nineteen", "getCount");
		return list != null ? list.size() : 0;
	}

	/**
	 * 返回指定位置的数据，return list.get(position)
	 */
	@Override
	public Object getItem(int position) {
		// Log.d("nineteen", "getItem");
		if (list != null && position < list.size()) {
			return list.get(position);
		}
		return null;
	}

	/**
	 * 获得指定位置的数据的行号
	 */
	@Override
	public long getItemId(int position) {
		// Log.d("nineteen", "getItemId");
		if (list != null && position < list.size()) {
			return position;
		}
		return 0;
	}

	/**
	 * 生成当前项，并数据绑定进去 position 当前项的位置 convertView 缓存的item项的view parent 父控件
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		if (convertView == null) {
			// Log.d("nineteen", "新生成");
			holder = new Holder();
			convertView = inflater.inflate(R.layout.parse_json_item, null);
			// 获得view里面的控件
			holder.pro_name = (TextView) convertView
					.findViewById(R.id.pro_name);
			holder.pro_price = (TextView) convertView
					.findViewById(R.id.pro_price);
			holder.pro_date = (TextView) convertView
					.findViewById(R.id.pro_date);

			convertView.setTag(holder);
		} else {
			// Log.d("nineteen", "使用缓存");
			holder = (Holder) convertView.getTag();
		}
		// 给控件绑定数据
		if (position < list.size()) {
			holder.pro_name.setText(list.get(position).pro_name);
			holder.pro_price.setText(list.get(position).pro_price);
			holder.pro_date.setText("发表时间：" + list.get(position).pro_date.substring(0, 10));

		}
		// 返回当前数据项
		return convertView;
	}

}
