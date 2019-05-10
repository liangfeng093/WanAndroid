package com.liangfeng.wanandroid.app

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.liangfeng.wanandroid.LogUtils.Companion.context
import com.liangfeng.wanandroid.features.login.RemoteDateManger
import com.xiehe.mobileportalsystem.crash.CrashHandler


/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc : APP最先执行的位置，主要进行一些初始化操作。不允许进行耗时操作
 */
class App : MultiDexApplication() {

    val MI_PUSH_TAG = "MI_PUSH_"

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)//分包操作
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        CrashHandler.init(this, "544935678@qq.com")
        LogUtils.init(LogUtils.VERBOSE, this)//正式上线环境level设置为LogUtils.NOLOG
        RemoteDateManger.getInstance()
        //初始化数据库


    }

}