package com.github.gyy.githubapp1.jiguang;

import android.content.Context;
import android.util.Log;

import com.github.gyy.githubapp1.sp.GlobalKey;
import com.github.gyy.githubapp1.sp.SharedPrefGlobal;
import com.github.gyy.githubapp1.utils.StringUtil;


import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 极光的别名，tag操作，会回调到这里
 */
public class MeJPushMessageReceiver extends JPushMessageReceiver {
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {

        super.onAliasOperatorResult(context, jPushMessage);
        int sequence = jPushMessage.getSequence();

        SharedPrefGlobal sp = new SharedPrefGlobal.Builder().setFileName(GlobalKey.FILE_USER).create(context);

        String phone = sp.get(GlobalKey.PHONE, "");

        //删除成功，如果电话不为空，则设置别名
        if (sequence == -1) {
            sp.put(GlobalKey.JPUSH_DELETE_ALIAS_ING, false);

            if (!StringUtil.isEmpty(phone)) {
                JPushInterface.resumePush(context);
                //设置别名
                JPushInterface.setAlias(context, 1, phone);
            }

        }
        Log.e("onAliasOperatorResult", sequence + "  " + phone);

    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);

        Log.e("收到自定义消息", customMessage.message);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);

        Log.e("收到通知", notificationMessage.notificationTitle);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
    }
}
