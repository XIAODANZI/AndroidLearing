package com.codera.myfirsthybridapp.plugin;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.json.JSONArray;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;

/**
 * 获取一个联系人的插件
 * Created by Administrator on 2016/2/16.
 */
public class OneContactPlugin extends StandardFeature {


    public void getFirstContactInfo(IWebview pWebview, JSONArray array) {
        // 参数的获取顺序与JS层传递的顺序一致
        String callBackID = array.optString(0);
        Context context = pWebview.getContext();
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String givenName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String phoneNumber = null;
            String resultString = "";

            resultString += "{\"givenName\":\"" + givenName + "\",\"numbers\":[";

            // 获取电话号码
            Cursor pCursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                    null,
                    null);
            if (pCursor != null) {
                while (pCursor.moveToNext()) {
                    phoneNumber = pCursor.getString(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    resultString += "{\"value\":\"" + phoneNumber + "\"},";
                }
                pCursor.close();
            }

            cursor.close();

            // 去掉最后一个逗号
            resultString = resultString.substring(0, resultString.length() - 1);
            resultString += "]}";

            System.out.println("信息=" + resultString);

            JSUtil.execCallback(pWebview, callBackID, JSONUtil.createJSONObject(resultString), JSUtil.OK, false);
        }
    }


}
