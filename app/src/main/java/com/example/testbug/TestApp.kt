package com.example.testbug

import android.app.Application

/**
 * @author hejinhua
 * @date 2022/11/3
 */
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.init(this)
    }
}