package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.yoochangwons.kotlinmissingcode.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d("life_cycle", "onCreate")

        val fragmentOne: FragmentOne = FragmentOne()

        // 프라그먼트에 data 를 넣어주는 방법
        // bundle 을 만들어주어 프라그먼트에 끼워 넣어 줘야 한다
        val bundle: Bundle = Bundle()
        bundle.putString("hello", "Hello!!")
        fragmentOne.arguments = bundle

        binding.btn.setOnClickListener {
            // 프라그먼트를 동적으로 작동하는 방법
            val fragmentManager: FragmentManager = supportFragmentManager

            // Transaction
            // 작업의 단위 -> 시작과 끝이 있다
            val fragmentTransaction = fragmentManager.beginTransaction() // 시작
            fragmentTransaction.replace(R.id.container, fragmentOne)

            // 끝을 내는 방법 2가지
            // commit() -> 시간 될 때 해 (좀더 안정적)
            // commitNow() -> 지금 당장해
            fragmentTransaction.commit()
        }

        binding.btn2.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            // 프라그먼트를 때는 방법
            // detach -> 한번 때면 다시 붙이지 못한다
            // remove -> 다시 붙이려고 할 때 사용한다
            fragmentTransaction.remove(fragmentOne)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }
}