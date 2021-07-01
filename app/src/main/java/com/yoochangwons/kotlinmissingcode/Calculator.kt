package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.kotlinmissingcode.databinding.ActivityCalculratorBinding

class Calculator : AppCompatActivity() {

    private lateinit var binding: ActivityCalculratorBinding

    var checkNew: String = "0"
    var checkOld: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculratorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.one.setOnClickListener {
            checkNew += "1"
            binding.result.text = checkNew
        }
        binding.two.setOnClickListener {
            checkNew += "2"
            binding.result.text = checkNew
        }
        binding.three
        binding.four
        binding.five
        binding.six
        binding.seven
        binding.eight
        binding.nine
        binding.zero
        binding.ca.setOnClickListener {
            checkNew = "0"
            checkOld = "0"
            binding.result.text = "0"
        }
        binding.plus.setOnClickListener {
            checkOld = (checkOld.toInt() + checkNew.toInt()).toString()
            checkNew = "0"
            binding.result.text = checkOld
        }
    }
}