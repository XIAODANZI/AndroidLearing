package com.chen.fragment;

import java.util.ArrayList;
import java.util.List;

import com.chen.adapterview.QQChild;
import com.chen.adapterview.QQFriendsAdapter;
import com.chen.adapterview.QQGroup;
import com.nineteen.myandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class QQFriendFragment extends Fragment {

	ExpandableListView expandableListView;
	List<QQGroup> list = new ArrayList<QQGroup>();
	String[] groupNames = { "朋友", "家人", "同学", "基友" };

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.qq_friend_list, null);
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.expandable);
		QQFriendsAdapter adapter = new QQFriendsAdapter(getActivity(), list,
				expandableListView);
		// 开始组装数据源
		for (int i = 0; i < groupNames.length; i++) {
			QQGroup group = new QQGroup();
			group.groupName = groupNames[i]; // 分组名
			group.childList = new ArrayList<QQChild>(); // 初始化chile列表
			list.add(group);
		}

		QQChild child = new QQChild();
		child.icon = R.drawable.bird_1;
		child.name = "张三";
		child.sign = "人不要脸，天下无敌";
		child.isOnLine = true;
		list.get(0).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_2;
		child.name = "李四";
		child.sign = "树不要皮，必死无疑";
		child.isOnLine = true;
		list.get(0).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_4;
		child.name = "小明";
		child.sign = "天要下雨，娘要嫁人, 超级无奈";
		child.isOnLine = false;
		list.get(0).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_5;
		child.name = "老爹";
		child.sign = "什么都不想说";
		child.isOnLine = true;
		list.get(1).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_6;
		child.name = "老公";
		child.sign = "谁都不是谁的老公，都是tmd的临时工";
		child.isOnLine = true;
		list.get(1).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_7;
		child.name = "老婆";
		child.sign = "老婆还是别人的好";
		child.isOnLine = false;
		list.get(1).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_1;
		child.name = "范大人";
		child.sign = "只许州官放火，不许百姓点灯";
		child.isOnLine = false;
		list.get(2).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_2;
		child.name = "小妖";
		child.sign = "男人好色，英雄本色";
		child.isOnLine = false;
		list.get(2).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_3;
		child.name = "断臂山";
		child.sign = "好基友，好利友";
		child.isOnLine = true;
		list.get(3).childList.add(child);

		child = new QQChild();
		child.icon = R.drawable.bird_3;
		child.name = "麒麟臂";
		child.sign = "来者不拒";
		child.isOnLine = false;
		list.get(3).childList.add(child);

		expandableListView.setAdapter(adapter);

		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// 事件未处理完毕， 会触发展开和收缩事件
				// true 事件处理完毕，不会触发展开和收缩事件
				return true;
			}
		});

		// 设置分组的收缩事件监听
		expandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {
					@Override
					public void onGroupCollapse(int groupPosition) {
					}
				});
		// 设置分组的展开事件
		expandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {
					@Override
					public void onGroupExpand(int groupPosition) {
					}
				});
		expandableListView.setOnChildClickListener(new OnChildClickListener() {
			/**
			 * parent ExpandableListView v 当前的child项的view groupPosition
			 * child项所在分组的位置 childPosition child所在的分组类的位置 id child项的行ID
			 */
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// 点击事件跳转到聊天的逻辑在这儿实现

				// 返回true，事件处理完毕
				// false 事件未处理完毕
				return false;
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

}
