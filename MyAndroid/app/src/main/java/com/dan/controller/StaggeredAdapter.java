package com.dan.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nineteen.myandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    LayoutInflater mInflater;
    Context context;
    List<String> mDatas;
    List<Integer> mHeights;

    public StaggeredAdapter(Context context, List<String> datas) {
        this.context = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_recyclerview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.txt.setText(mDatas.get(position));
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


