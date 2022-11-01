package com.example.testbug

import android.os.Bundle
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
        mBinding.bean = bean

        GlobalScope.launch {
            delay(3000)

            bean.strLiveData.postValue("呵呵呵呵呵呵呵呵")
        }
    }

    class Bean {
        val strLiveData = MutableLiveData("Hello World")
    }
}