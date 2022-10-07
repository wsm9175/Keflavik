package com.example.keflavik.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class ContextUtil {

    static public final String prefName = "Keflavik";
    static public final String LOGIN_TOKEN = "LOGIN_TOKEN";

    public static void setLoginToken(Context context, String token){
        //메모장 열기
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_TOKEN, token).apply();
    }

    public static String getLoginToken(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_TOKEN, ""); //토큰이 없으면 빈칸을 날림
    }

}
