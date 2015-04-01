package com.example.kobayashishinji.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kobayashi.shinji on 2015/03/10.
 */
public class Utility {

    public static void savePreferences(Context context, int userId, String userName, String mail){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.putString("userName", userName);
        editor.putString("mail", mail);
        editor.commit();
    }

    public static Object getUserData(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        if( key.equals("userId")) {
            return preferences.getInt(key, -1);
        }else {
            return preferences.getString(key, "");
        }
    }

}
