package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yoochangwons.kotlinmissingcode.databinding.ActivityPhoneBookDetailBinding

class PhoneBookDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Glide.with(this)
            .load("https://file.mk.co.kr/meet/neds/2021/04/image_readtop_2021_330747_16177500644599916.jpg")
            .into(binding.personDetailImage)

        val name = intent.getStringExtra("name")

        binding.personDetailText.text = name
    }
}