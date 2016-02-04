package com.chen.learn.reflex_mechanism.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/11/24.
 */
public class ActionReceiver extends BroadcastReceiver {

    String className = "com.chen.learn.reflex_mechanism.controller.ReflexMechanismTestActivity";
    String methodName = "showToast";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Class ownerclass = Class.forName(className);
            Method method = ownerclass.getMethod(methodName, new Class[]{String.class});
            method.invoke(null, "java反射机制");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}
