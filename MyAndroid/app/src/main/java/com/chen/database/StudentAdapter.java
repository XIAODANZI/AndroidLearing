package com.chen.database;

import java.util.List;

import com.nineteen.myandroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {

	List<StudentBean> list;
	LayoutInflater inflater;

	public StudentAdapter(Context context, List<StudentBean> list) {
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
			convertView = inflater
					.inflate(R.layout.student_item, parent, false);
			holder = new Holder();
			holder.stuId = (TextView) convertView.findViewById(R.id.stu_id);
			holder.stuName = (TextView) convertView.findViewById(R.id.stu_name);
			holder.stuGender = (TextView) convertView
					.findViewById(R.id.stu_gender);
			holder.stuClass = (TextView) convertView
					.findViewById(R.id.stu_class);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if (position < list.size()) {
			holder.stuId.setText(list.get(position).stuId + "");
			holder.stuName.setText(list.get(position).stuName);
			if (list.get(position).stuGender == 0) {
				holder.stuGender.setText("男");
			} else {
				holder.stuGender.setText("女");
			}
			holder.stuClass.setText(list.get(position).stuClass);
		}

		return convertView;
	}

	class Holder {
		TextView stuId;
		TextView stuName;
		TextView stuGender;
		TextView stuClass;
	}

}
