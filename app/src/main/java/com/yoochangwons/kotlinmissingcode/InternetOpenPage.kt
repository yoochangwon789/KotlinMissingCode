package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.kotlinmissingcode.databinding.ActivityInternetOpenPageBinding

class InternetOpenPage : AppCompatActivity() {

    private lateinit var binding: ActivityInternetOpenPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternetOpenPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.openInternetBtn.setOnClickListener {
            val internetUri = binding.uri.text.toString()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(internetUri))
            startActivity(intent)
        }
    }
}