package com.chen.fifth.test;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class LogAdapter extends BaseAdapter {

	List<LogBean> list;
	LayoutInflater inflater;

	public LogAdapter(Context context, List<LogBean> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.fifth_list_log_item,
					parent, false);
			holder = new Holder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if (position < list.size()) {
			holder.title.setText(list.get(position).title + "");
			holder.date.setText(list.get(position).date);
		}

		return convertView;
	}

	class Holder {
		TextView title;
		TextView date;
	}

}
