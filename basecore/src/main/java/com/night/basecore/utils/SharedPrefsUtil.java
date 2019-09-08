package com.night.basecore.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {

    private static final String FILENAME="data";

    //读取数据->String
    public static String getString(Context context, String key,String defaultStr){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defaultStr);
    }

    //读取数据->Int
    public static int getInt(Context context, String key,int defaultInt){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,defaultInt);
    }

    //读取数据->boolean
    public static boolean getBoolean(Context context, String key,boolean defaultBoolean){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,defaultBoolean);
    }

    //写入数据-> String
    public static boolean writeStr(Context context,String key,String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,content);
        return editor.commit();
    }

    //写入数据-> int
    public static boolean writeInt(Context context,String key,int number){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,number);
        return editor.commit();
    }

    //写入数据-> boolean
    public static boolean writeStr(Context context,String key,boolean b){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(key,b);
        return editor.commit();
    }

    //删除数据-> String
    public static boolean removeStr(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    //清空数据
    public static boolean clearAllData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }

}
