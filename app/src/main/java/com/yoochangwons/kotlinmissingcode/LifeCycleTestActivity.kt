package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LifeCycleTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_test)

        Log.d("LifeCycleTest", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycleTest", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleTest", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycleTest", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycleTest", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycleTest", "onDestroy")
    }
}