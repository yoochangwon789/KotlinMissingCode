package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

        // EditText 의 콜백을 감지하는 리스너
        binding.uri.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit", "beforeTextChanged : " + s)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("edit", "onTextChanged : " + s)
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() == "abc") Log.d("edit", "text is abc")
                Log.d("edit", "afterTextChanged : " + s)
            }
        })
    }
}