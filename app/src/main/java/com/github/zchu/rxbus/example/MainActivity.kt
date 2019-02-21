package com.github.zchu.rxbus.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import android.R.attr.button
import android.util.Log
import android.view.View
import android.widget.TextView
import com.github.zchu.rxbus.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers


class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hermes.getVersion();
        setContentView(R.layout.activity_main)
        textView = findViewById<View>(R.id.tv) as TextView
        findViewById<View>(R.id.button).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                startActivity(Intent(applicationContext, SecondActivity::class.java))
            }
        })
        findViewById<View>(R.id.post_event).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                object : Thread() {
                    override fun run() {
                        try {
                            Thread.sleep(3000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        Log.v("EricZhao", "post")
                        RxBus.getDefault().postRemote(Event1("This is an event from the main process."))
                    }
                }.start()
            }
        })


        findViewById<View>(R.id.start_service).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                startService(Intent(applicationContext, MyService::class.java))
            }
        })
        //        findViewById<View>(R.id.exception).setOnClickListener(new View.OnClickListener {
        //            @Override
        //            public void onClick(View view) {
        //                throw new RuntimeException("This is an exception.");
        //            }
        //        });
        RxBus.getDefault().toObservable(Event1::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView!!.text = it.toString()
                Log.v("EricZhao", "Event1 : MainActivity receives an event: ${it.toString()}")
            }
        RxBus.getDefault().toObservable(Event2::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView!!.text = it.toString()
                Log.v("EricZhao", "Event2 : MainActivity receives an event: ${it.toString()}")
            }
        RxBus.getDefault().toObservable(Event3::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView!!.text = it.toString()
                Log.v("EricZhao", "Event3 : MainActivity receives an event: ${it.toString()}")
            }
    }



    override fun onDestroy() {
        super.onDestroy()
        RxBus.getDefault().removeAllStickyEvents()

    }
}
