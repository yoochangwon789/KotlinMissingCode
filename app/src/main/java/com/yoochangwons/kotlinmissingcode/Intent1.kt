package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            startActivity(intent2)
        }
    }
}