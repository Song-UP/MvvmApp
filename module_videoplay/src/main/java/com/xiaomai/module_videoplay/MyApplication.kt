package com.xiaomai.module_videoplay

import android.app.Application
import com.xiaomai.module_videoplay.utils.AppManager

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        AppManager.init(this)
    }
}