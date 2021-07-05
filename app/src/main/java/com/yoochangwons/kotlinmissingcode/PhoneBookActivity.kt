package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.kotlinmissingcode.databinding.ActivityPhoneBookBinding

class PhoneBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}