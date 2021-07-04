package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.yoochangwons.kotlinmissingcode.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.threadBtn.setOnClickListener {
            val runnable: Runnable = Runnable {
                Log.d("thread-1", " Thread is made")
            }

            val thread: Thread = Thread(runnable)
            thread.start()
        }

        Thread(object : Runnable {
            override fun run() {
                Log.d("thread-2", " Thread is made")
            }
        }).start()


        Thread(Runnable {
            Thread.sleep(2000)
            Log.d("thread-3", " Thread is made")
            binding.threadBtn.setBackgroundColor(getColor(R.color.purple_200))
        }).start()
    }
}