package com.example.testbug

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.testbug.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val bean by lazy {
        Bean()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.bean = bean

        GlobalScope.launch {
            delay(3000)

            bean.strLiveData.postValue("呵呵呵呵呵呵呵呵")
        }

        mBinding.tv.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        Test.liveData.observe(this) {
            Log.d("XXXXXXX", "${this.javaClass.simpleName} ${lifecycle.currentState}")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXXXXXX", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXXXXXX", "onPause")
    }

    class Bean {
        val strLiveData = MutableLiveData("Hello World")
    }
}