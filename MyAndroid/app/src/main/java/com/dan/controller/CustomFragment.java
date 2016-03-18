package com.dan.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nineteen.myandroid.R;

/**
 * Created by Administrator on 2016/2/27.
 */
public class CustomFragment extends Fragment {

    String mTitle;

    View view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.test_textview, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView_0);
        textView.setText(mTitle);

        view.findViewById(R.id.textView_1).setVisibility(View.GONE);
        view.findViewById(R.id.textView_2).setVisibility(View.GONE);

        return view;
    }
}
