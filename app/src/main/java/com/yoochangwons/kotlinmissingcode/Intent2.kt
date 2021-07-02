package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yoochangwons.kotlinmissingcode.databinding.ActivityIntent2Binding

class Intent2 : AppCompatActivity() {

    private lateinit var binding: ActivityIntent2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntent2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resultBtn.setOnClickListener {
            val number1 = intent.getIntExtra("number1", 0)
            val number2 = intent.getIntExtra("number2", 0)

            Log.d("number1", "$number1")
            Log.d("number2", "$number2")

            // 요청받은 값 결과를 보내는 코드
            val result = number1 + number2
            val resultIntent = Intent()
            resultIntent.putExtra("result", result)
            setResult(Activity.RESULT_OK, resultIntent)
            // Activity 종료
            finish()
        }
    }
}