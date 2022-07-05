package com.gzf.xilv.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void show(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context,int stringId) {
        Toast.makeText(context, context.getResources().getString(stringId), Toast.LENGTH_SHORT).show();
    }
}
