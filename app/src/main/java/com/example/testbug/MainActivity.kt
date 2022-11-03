package com.example.testbug

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
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

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                Log.d("XXXXXXX", "进后台了")
            }
        })

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
//            Log.d("XXXXXXX", "${this.javaClass.simpleName} ${lifecycle.currentState}")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXXXXXX", "Act1 onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXXXXXX", "Act1 onPause")
    }

    class Bean {
        val strLiveData = MutableLiveData("Hello World")
    }
}