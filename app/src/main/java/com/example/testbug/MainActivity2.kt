package com.example.testbug

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Test.liveData.observe(this) {
//            Log.d("XXXXXXX", "${this.javaClass.simpleName} ${lifecycle.currentState}")
        }

        GlobalScope.launch {
            delay(1000)

            Test.liveData.postValue(111)
        }

        Thread.sleep(4000)
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXXXXXX", "Act2 执行了resume")
    }
}