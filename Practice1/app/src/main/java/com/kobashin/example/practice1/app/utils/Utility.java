package com.kobashin.example.practice1.app.utils;

import com.kobashin.example.practice1.app.R;
import com.kobashin.example.practice1.app.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by kobayashi.shinji on 2015/02/24.
 */
public class Utility {

    public static void nextActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }

    public static void saveUserIdToPreferences(Context context, String userId) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(getUserDataInString(context, "userIds").equals("")){
            editor.putString("userIds", getUserDataInString(context, "userIds") + userId);
        }else {
            editor.putString("userIds", getUserDataInString(context, "userIds") + "," + userId);
        }
        editor.apply();
    }

    public static void deleteUserIdInPreferences(Context context, String userId) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = getUserDataInString(context, "userIds");
        if(text.equals("")){
        }else {
            text = text.replace(userId, "");
            editor.putString("userIds", text);
        }
        editor.apply();
    }

    public static void saveUserToPreferences(Context context, String userId, String name, String mail,
            String phone, String addr, String memo) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName" + userId, name);
        editor.putString("userMailAddr" + userId, mail);
        editor.putString("userPhone" + userId, phone);
        editor.putString("userAddr" + userId, addr);
        editor.putString("userMemo" + userId, memo);
        editor.apply();
    }

    public static void saveUserToPreferences(Context context, User user) {
        String userId = String.valueOf(user.getId());
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName" + userId, user.getName());
        editor.putString("userMailAddr" + userId, user.getMailAddr());
        editor.putString("userPhone" + userId, user.getPhone());
        editor.putString("userAddr" + userId, user.getAddress());
        editor.putString("userMemo" + userId, user.getMemo());
        editor.apply();
        saveUserIdToPreferences(context, userId);
    }

    public static void deleteUserToPreferences(Context context, int userId) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userName" + userId);
        editor.remove("userMailAddr" + userId);
        editor.remove("userPhone" + userId);
        editor.remove("userAddr" + userId);
        editor.remove("userMemo" + userId);
        editor.apply();
        deleteUserIdInPreferences(context, String.valueOf(userId));
    }



    public static User getUserData(Context context, String id){
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setName(getUserDataInString(context, "userName" + id));
        user.setMailAddr(getUserDataInString(context, "userMailAddr" + id));
        user.setPhone(getUserDataInString(context, "userPhone" + id));
        user.setAddress(getUserDataInString(context, "userAddr" + id));
        user.setMemo(getUserDataInString(context, "userMemo" + id));
        // TODO: write icon of user to preference
        user.setIconId(R.mipmap.ic_launcher);
        return user;
    }


    public static String getUserDataInString(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
}
