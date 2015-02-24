package com.example.kobayashishinji.listviewsample.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kobayashi.shinji on 2015/02/24.
 */
public class Utility {
    public static void nextActivity(Activity activity, Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }
}
