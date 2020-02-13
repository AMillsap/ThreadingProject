package com.example.threadingproject

import android.os.AsyncTask
import android.util.Log
import org.greenrobot.eventbus.EventBus
import java.lang.StringBuilder
import kotlin.random.Random

class AsyncTaskSorting : AsyncTask<String, String, String>()
{
    var arrayOfRandInt = IntArray(1000)
    var sortedArray : String = ""

    override fun onPreExecute() {
        super.onPreExecute()
    }

    //Runs on Worker Thread
    //The task that needs to be run
    override fun doInBackground(vararg params: String?): String
    {
        for(i in 0 until arrayOfRandInt.size)
        {
            arrayOfRandInt[i] = Random.nextInt(0,100)
        }
        arrayOfRandInt.sort()

        for(i in 0 until arrayOfRandInt.size)
        {
            sortedArray += arrayOfRandInt[i].toString() +", "
        }

        return sortedArray
    }

    //Runs on Main Thread
    //Used to get updates about the task status
    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
    }

    //Runs on Main Thread
    //Reports the result of the task
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        EventBus.getDefault().post(AsyncTaskEvent(result?: "No Result"))

    }

}