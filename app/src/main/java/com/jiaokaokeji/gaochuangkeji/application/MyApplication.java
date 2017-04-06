package com.jiaokaokeji.gaochuangkeji.application;

import android.app.Application;


import com.jiaokaokeji.gaochuangkeji.myclass.signview.ResolutionUtil;

import org.xutils.BuildConfig;
import org.xutils.x;

import cn.smssdk.SMSSDK;


/**
 * Created by Administrator on 2016/12/8.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        ResolutionUtil.getInstance().init(this);
        SMSSDK.initSDK(this,"1c8cc63c2debc","e1ef2b7d9152a9c99609128c1c1127b7");//
    }
}
