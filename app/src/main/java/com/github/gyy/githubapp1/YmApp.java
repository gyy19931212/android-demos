package com.github.gyy.githubapp1;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.bun.miitmdid.core.JLibrary;
import com.github.gyy.githubapp1.jiguang.JPushUtil;

public class YmApp extends Application {



    private static volatile YmApp instance = null;
    public static YmApp getInstance() {
        return instance;
    }

    public static String oaid;
    private static boolean isSupportOaid=true;
    private static int errorCode;

    public static String getOaid() {
        return oaid;
    }
    public static String getErrorCode() {
        return String.valueOf(errorCode);
    }

    public static boolean isSupportOaid() {
        return isSupportOaid;
    }

    public static void setIsSupportOaid(boolean isSupportOaid) {
        YmApp.isSupportOaid = isSupportOaid;
    }
    public static void setIsSupportOaid(boolean isSupportOaid,int ErrorCode) {
        YmApp.isSupportOaid = isSupportOaid;
        YmApp.errorCode=ErrorCode;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化 JLibrary
        try {
            JLibrary.InitEntry(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化极光
        JPushUtil.init(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }
}
