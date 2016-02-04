package com.chen.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chen.adapterview.Mybean;
import com.chen.fragment.Shakespeare;
import com.nineteen.myandroid.R;

public class ScriptFragment extends Fragment implements OnItemClickListener {

	ListView listView;

	List<Mybean> list = new ArrayList<Mybean>();
	ArrayAdapter<String> adapter;
	final static int REQUESTCODE_CONTENTACTIVITY = 110;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.script_listview, null);

		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1);
		adapter.addAll(Shakespeare.TITLES);
		listView = (ListView) view.findViewById(R.id.listview_script);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Intent intent = new Intent(getActivity(), ContentActivity.class);
		intent.putExtra("ScriptFragment", position);
		if (intent != null && intent.getExtras() != null
				&& !intent.getExtras().isEmpty()) {
			startActivityForResult(intent, REQUESTCODE_CONTENTACTIVITY);
			System.out.println(position);

		}
	}

}
