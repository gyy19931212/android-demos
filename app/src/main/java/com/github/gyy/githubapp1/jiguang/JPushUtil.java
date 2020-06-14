package com.github.gyy.githubapp1.jiguang;

import android.content.Context;
import android.text.TextUtils;

import com.github.gyy.githubapp1.sp.GlobalKey;
import com.github.gyy.githubapp1.sp.SharedPrefGlobal;

import cn.jpush.android.api.JPushInterface;

public final class JPushUtil {

    /**
     * 设置别名
     * @param context
     * @param value
     */
    public static void setAlias(Context context, String value) {

        SharedPrefGlobal sp = new SharedPrefGlobal.Builder().setFileName(GlobalKey.FILE_USER).create(context);

        //没有init，则init
        if (!sp.get(GlobalKey.JPUSH_INIT_DONE, false)) {

            //改变 JPUSH_INIT_DONE
            sp.put(GlobalKey.JPUSH_INIT_DONE, true);
            //初始化
            JPushInterface.setDebugMode(true);
            JPushInterface.init(context);
        }

        //别名不是在删除中，则直接设置别名；（如果别名在删除中，则在别名删除的成功回调中，设置新的别名）
        if (!sp.get(GlobalKey.JPUSH_DELETE_ALIAS_ING, false)) {

            JPushInterface.resumePush(context);
            //设置别名
            JPushInterface.setAlias(context, 1, value);

        }



    }


    /**
     * 删除别名
     * @param context
     */
    public static void deleteAlias(Context context) {
        SharedPrefGlobal sp = new SharedPrefGlobal.Builder().setFileName(GlobalKey.FILE_USER).create(context);
        sp.put(GlobalKey.JPUSH_DELETE_ALIAS_ING, true);

        JPushInterface.deleteAlias(context,-1);
        JPushInterface.stopPush(context);
    }

    /**
     * 极光init
     * @param context
     */
    public static void init(Context context) {

        SharedPrefGlobal sp = new SharedPrefGlobal.Builder().setFileName(GlobalKey.FILE_USER).create(context);
        String phone = sp.get(GlobalKey.PHONE, "");
        boolean empty = TextUtils.isEmpty(phone);

        //改变 JPUSH_INIT_DONE
        sp.put(GlobalKey.JPUSH_INIT_DONE, !empty);
        if(!empty)
        {
            JPushInterface.setDebugMode(true);
            JPushInterface.init(context);
        }
    }

}
