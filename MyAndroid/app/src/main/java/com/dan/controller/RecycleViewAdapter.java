package com.dan.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nineteen.myandroid.R;

import java.util.List;

/**
 * 一般的adapter
 * Created by Administrator on 2016/2/24.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    LayoutInflater mInflater;
    Context context;
    List<String> mDatas;

    public RecycleViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_recyclerview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txt.setText(mDatas.get(position));
    }

    // 删除指定的Item
    public void removeData(int position) {
        mDatas.remove(position);
        // 通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);
    }


    // 在指定位置添加一个新的Item
    public void adddItem(int position) {
        mDatas.add(position, String.valueOf("hehe"));
        // 通知RcyclerView控件在指定位置添加了Item
        notifyItemInserted(position);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }
}

