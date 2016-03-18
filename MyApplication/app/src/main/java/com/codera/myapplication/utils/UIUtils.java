package com.codera.myapplication.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * UI帮助类
 * Created by Administrator on 2016/2/22.
 */
public class UIUtils {

    public static int convertDptoPx(Context context, float dp) {
        int px = (int) Math.ceil(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()));
        return px;
    }

}
