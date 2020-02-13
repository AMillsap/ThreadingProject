package com.example.threadingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runThreadRunnable()
        val asyncTask = AsyncTaskSorting()
        asyncTask.execute()
    }

    override fun onStart()
    {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop()
    {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAsyncTaskEvent(event: AsyncTaskEvent)
    {
        tvAsyncOutput.text = event.resultMessage
    }

    fun runThreadRunnable()
    {
        val pi = PI.toString()
        var piToTwelve : String = ""
        val thread = Thread(Runnable
        {
            for(i in 0 until 13)
            {
                piToTwelve += pi[i]
            }
            runOnUiThread{tvRunnablesOutput.text = piToTwelve}
        })
        thread.start()
    }
}
