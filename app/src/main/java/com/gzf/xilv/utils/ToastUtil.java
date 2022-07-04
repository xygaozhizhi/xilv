package com.gzf.xilv.utils;

import android.widget.Toast;

import com.gzf.xilv.XiLvApp;

public class ToastUtil {
    public static void show(String msg) {
        Toast.makeText(XiLvApp.context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(int stringId) {
        Toast.makeText(XiLvApp.context, XiLvApp.context.getText(stringId), Toast.LENGTH_SHORT).show();
    }
}
