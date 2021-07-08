package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yoochangwons.kotlinmissingcode.databinding.ActivitySharedPreferenceBinding

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferenceBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // SharePreference 가져온 후 저장하는 방법

    // Mode
        // - MODE_PRIVATE : 생성한 application 에서만 사용 가능
        // - MODE_WORLD_READABLE : 다른 application 사용가능 -> 읽을수만 있다
        // - MODE_WORLD_WRITEABLE : 다른 application 사용가능 -> 기록도 가능

        // SharePreference 데이터 주입방법
        // editor 를 통해서 data 주입
//        val editor: SharedPreferences.Editor = sharedPreference.edit()
//        editor.putString("hello", "안녕하세요")
//        editor.commit()

        // sp1 -> SharedPreference
        //        (Key-Value) -> ("Hello", "안녕하세요")
        // sp2 -> SharedPreference
        //        (Key-Value) -> ("Hello", "안녕하세요11")

        binding.saveButton.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("hello", "안녕하세요")
            editor.putString("goodbye", "안녕히가세요")
            editor.apply()
        }

        binding.selectedButton.setOnClickListener {
            // SharePreference 값을 불러오는 방법
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val value1 = sharedPreference.getString("hello", "데이터 없음")
            val value2 = sharedPreference.getString("goodbye", "데이터 없음")
            Log.d("key-value", "Value 1 : $value1")
            Log.d("key-value", "Value 2 : $value2")
        }

        binding.deleteButton.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("hello")
            editor.apply()
        }

        binding.deleteAllButton.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.apply()
        }
    }
}
