package com.github.zchu.rxbus.example

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.github.zchu.rxbus.RxBus


class MyService :Service(){

    override fun onCreate() {
        super.onCreate()
        RxBus.getDefault().postRemote(Event3("Send a message during the creation of MyService."))
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "MyService started", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

}