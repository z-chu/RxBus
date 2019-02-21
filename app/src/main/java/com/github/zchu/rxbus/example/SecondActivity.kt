package com.github.zchu.rxbus.example

import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.zchu.rxbus.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.post_event).setOnClickListener {
            RxBus.getDefault().postRemote(Event2("This is an event from the sub-process."))
        }

        findViewById<View>(R.id.kill_process).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                RxBus.getDefault().reset()
                // The above statement is actually useless, for there is no enough time for disconnecting.
                // So once this button is pressed, you will see a DeadObjectException as long as you
                // send an event between processes.
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        })
        RxBus.getDefault().toObservable(Event1::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tv.text = it.toString()
                Log.v("EricZhao", "Event1 : SecondActivity receives an event: $it");

            }
        RxBus.getDefault().toObservable(Event2::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tv.text = it.toString()
                Log.v("EricZhao", "Event1 : SecondActivity receives an event: $it");

            }
        RxBus.getDefault().toObservable(Event3::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tv.text = it.toString()
                Log.v("EricZhao", "Event1 : SecondActivity receives an event: $it");

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        // RxBus.getDefault().unregister(this)
    }


}