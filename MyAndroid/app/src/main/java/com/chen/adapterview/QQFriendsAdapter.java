package com.chen.adapterview;

import java.util.List;

import com.nineteen.myandroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class QQFriendsAdapter extends BaseExpandableListAdapter {
	LayoutInflater inflater; // 布局加载器
	List<QQGroup> list; // 好友列表数据源
	Context mContext; //上下文
	ExpandableListView expandableListView;
	// 构造器
	public QQFriendsAdapter(Context context, List<QQGroup> list, ExpandableListView expandableListView) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
		this.mContext = context;
		this.expandableListView = expandableListView;
	}
	/**
	 * 返回分组的数量, 也就是有多少个分组
	 */
	@Override
	public int getGroupCount() {
		if (list != null) {
			return list.size(); // 分组的数量
		}
		return 0;
	}
	/**
	 * 返回指定groupPosition分组的child的数量 返回某个分组下面的成员的数量
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		if (list != null && list.get(groupPosition).childList != null) {
			return list.get(groupPosition).childList.size();
		}
		return 0;
	}
	/**
	 * 返回指定分组的数据 返回某个分组的数据
	 */
	@Override
	public Object getGroup(int groupPosition) {
		if (list != null && groupPosition < list.size()) {
			return list.get(groupPosition);
		}
		return null;
	}
	/**
	 * 返回指定groupPosition分组下面的指定childPosition的数据 就是返回某个分组下面，某个成员
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if (list != null && list.get(groupPosition) != null
				&& list.get(groupPosition).childList != null) {
			return list.get(groupPosition).childList.get(childPosition);
		}
		return null;
	}
	/**
	 * 返回指定分组的行ID
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * 返回指定分组下面的指定child的行ID
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * 是否拥有稳定ID
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * 生成分组的数据项View，并绑定数据 groupPosition 分组的位置 isExpanded 是否已展开 convertView
	 * 缓存的View parent 父控件
	 */
	@Override
	public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
		final GroupHolder groupHolder;
		if(convertView == null) {
			groupHolder = new GroupHolder();
			convertView = inflater.inflate(R.layout.qq_group_item, parent, false);
			groupHolder.indicator = (ImageView) convertView.findViewById(R.id.group_indicator);
			groupHolder.groupName = (TextView) convertView.findViewById(R.id.group_name);
			groupHolder.online = (TextView) convertView.findViewById(R.id.group_online);
			groupHolder.num = (TextView) convertView.findViewById(R.id.group_num);
			convertView.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) convertView.getTag();
		}
		if(isExpanded) { //如果现在的状态是展开的
			groupHolder.indicator.setImageResource(R.drawable.bird_1);
			System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
		} else {//如果现在的状态是收缩的
			groupHolder.indicator.setImageResource(R.drawable.bird_2);
			System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
		}
		if(groupPosition < list.size()) {
			groupHolder.groupName.setText(list.get(groupPosition).groupName);
			//在线的数量和总数需要去计算
			if(list.get(groupPosition).childList != null) {
				groupHolder.num.setText(list.get(groupPosition).childList.size() + "");
				int online = 0;
				for(int i=0; i< list.get(groupPosition).childList.size(); i++) {
					if(list.get(groupPosition).childList.get(i).isOnLine) {
						online++;
					}
				}
				groupHolder.online.setText(online + "");
			}
		}
		
		
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isExpanded) {
					Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.qq_group_colapse);
					//groupHolder.indicator.setAnimation(animation);
					groupHolder.indicator.startAnimation(animation);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}
						@Override
						public void onAnimationRepeat(Animation animation) {
						}
						@Override
						public void onAnimationEnd(Animation animation) {
							expandableListView.collapseGroup(groupPosition);
						}
					});
				} else {
					Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.qq_group_expand);
					//groupHolder.indicator.setAnimation(animation);
					groupHolder.indicator.startAnimation(animation);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}
						@Override
						public void onAnimationRepeat(Animation animation) {
						}
						@Override
						public void onAnimationEnd(Animation animation) {
							expandableListView.expandGroup(groupPosition,true);
						}
					});
				}
			}
		});
		
		
		/*//给这个项设置一个触屏事件
		convertView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					System.out.println("ACTION_DOWN isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
					if(isExpanded) {//如果是展开的，点击的时候，应该要收缩
						Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.qq_group_colapse);
						groupHolder.indicator.setAnimation(animation);
						groupHolder.indicator.startAnimation(animation);
						animation.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								if(isExpanded) { //如果现在的状态是展开的
									groupHolder.indicator.setImageResource(R.drawable.indicator_expand);
									System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
								} else {//如果现在的状态是收缩的
									groupHolder.indicator.setImageResource(R.drawable.indicator_normal);
									System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
								}
							}
						});
					} else {//应该要展开
						Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.qq_group_expand);
						groupHolder.indicator.setAnimation(animation);
						groupHolder.indicator.startAnimation(animation);
						animation.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								if(isExpanded) { //如果现在的状态是展开的
									groupHolder.indicator.setImageResource(R.drawable.indicator_expand);
									System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
								} else {//如果现在的状态是收缩的
									groupHolder.indicator.setImageResource(R.drawable.indicator_normal);
									System.out.println("isExpanded =" + isExpanded + "groupPosition=" + groupPosition);
								}
							}
						});
					}
					break;
				}
				//一定要返回false，要事件下发下去，就可以触发group项的展开和收缩事件
				//返回true，就不会触发group的展开和收缩事件
				return false;
			}
		});*/
		return convertView;
	}

	/**
	 * 生成child项，并绑定数据 groupPosition 分组的位置 childPosition child在组内的位置 isLastChild
	 * 是否是最后一个child convertView 缓存的View parent 父控件
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder childHolder;
		if(convertView == null) {
			childHolder = new ChildHolder();
			convertView = inflater.inflate(R.layout.qq_child_item, parent, false);
			childHolder.icon = (ImageView) convertView.findViewById(R.id.child_icon);
			childHolder.childName = (TextView) convertView.findViewById(R.id.chind_name);
			childHolder.childSign = (TextView) convertView.findViewById(R.id.child_sign);
			convertView.setTag(childHolder);
		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		if(groupPosition < list.size() && list.get(groupPosition).childList != null && childPosition < list.get(groupPosition).childList.size()) {
			childHolder.icon.setBackgroundResource(list.get(groupPosition).childList.get(childPosition).icon);
			childHolder.childName.setText(list.get(groupPosition).childList.get(childPosition).name);
			childHolder.childSign.setText(list.get(groupPosition).childList.get(childPosition).sign);
			if(!list.get(groupPosition).childList.get(childPosition).isOnLine) {
				childHolder.icon.setImageResource(R.drawable.qq_offline_bg); //设置一个蒙层，表示不在线
			}else {
				childHolder.icon.setImageResource(R.drawable.qq_online_bg); //在线标识
			}
		}
		return convertView;
	}

	/**
	 * 指定分组下面的指定的child是否可选中 一定要返回true，否则此项就不可点击
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	class GroupHolder {
		ImageView indicator;
		TextView groupName;
		TextView online;
		TextView num;
	}
	
	class ChildHolder{
		ImageView icon;
		TextView childName;
		TextView childSign;
	}
}
