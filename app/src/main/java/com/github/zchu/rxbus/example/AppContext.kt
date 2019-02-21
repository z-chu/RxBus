package com.github.zchu.rxbus.example

import android.app.Application
import android.util.Log
import com.github.zchu.rxbus.RxBus


class AppContext :Application() {
    override fun onCreate() {
        super.onCreate()
        //        Thread.setDefaultUncaughtExceptionHandler(
        //                new Thread.UncaughtExceptionHandler() {
        //                    @Override
        //                    public void uncaughtException(Thread thread, Throwable throwable) {
        //                        Log.v("EricZhao", "Crash in Process "+ Process.myPid() + ", Thread " + thread.getName());//, throwable);
        //                    }
        //                });
        Log.v("EricZhao", "Starting Process " + android.os.Process.myPid())
        RxBus.getDefault().connectRemote(this)
        //The following is to check whether the potential bug caused by a dead lock
        //in the main thread has been fixed.
        RxBus.getDefault().post("Event posted before connection from sub-process")
    }
}