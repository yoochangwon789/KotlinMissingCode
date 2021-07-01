package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yoochangwons.kotlinmissingcode.databinding.ActivityLinstenerBinding

class Listener : AppCompatActivity() {

    private lateinit var binding: ActivityLinstenerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinstenerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 람다방식
        binding.hello.setOnClickListener {
            Log.d("click", "Click!!")
        }

//        // 익명함수 방식
//        binding.hello.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
//            }
//        })

          // 이름이 필요한 방식
//        val check = object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
//            }
//        }
//
//        binding.hello.setOnClickListener(check)
    }
}