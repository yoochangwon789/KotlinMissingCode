package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yoochangwons.kotlinmissingcode.databinding.ActivityIntentBinding

class Intent1 : AppCompatActivity() {

    private lateinit var binding: ActivityIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.changeActivity.setOnClickListener {
//            val intent = Intent(this@Intent1, Intent2::class.java)
//            intent.putExtra("number1", 1)
//            intent.putExtra("number2", 2)
//            startActivity(intent)

            val intent2 = Intent(this@Intent1, Intent2::class.java)
            intent2.apply {
                putExtra("number1", 1)
                putExtra("number2", 2)
            }
            startActivityForResult(intent2, 200)
        }
    }

    // Intent 결과 값을 받는 코드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            Log.d("number", "" + requestCode)
            Log.d("number", "" + resultCode)
            val result = data?.getIntExtra("result", 0)
            Log.d("result", "" + result)
        }
    }
}