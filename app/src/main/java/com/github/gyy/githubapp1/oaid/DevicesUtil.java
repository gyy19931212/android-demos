package com.github.gyy.githubapp1.oaid;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.gyy.githubapp1.YmApp;

public class DevicesUtil {

    /**
     * 初始化
     */
    public static void init() {
        //获取OAID等设备标识符
        MiitHelper miitHelper = new MiitHelper(new MiitHelper.AppIdsUpdater() {
            @Override
            public void OnIdsAvalid(@NonNull String ids) {
                Log.e("MainActivity-- ", ids);
                YmApp.oaid = ids;
            }
        });

        miitHelper.getDeviceIds(YmApp.getInstance());
    }


    /**
     * 获取唯一标识idfa
     * @return
     */
    public static String getOaid() {
        String idfa = "";
        //isSupportOaid，一定要在 init方法调用完毕后，再调用
        if (YmApp.isSupportOaid()) {
            idfa = YmApp.getOaid();

        }
        Log.e("MainActivity", YmApp.isSupportOaid() + "");
        Log.e("MainActivity", idfa);

        return idfa;
    }



}
