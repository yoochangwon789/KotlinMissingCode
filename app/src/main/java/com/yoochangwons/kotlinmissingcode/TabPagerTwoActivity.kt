package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.yoochangwons.kotlinmissingcode.databinding.ActivityTabPagerTwoBinding

class TabPagerTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabPagerTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerTwoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tabTwoLayout.addTab(binding.tabTwoLayout.newTab().setText("ONE"))
        binding.tabTwoLayout.addTab(binding.tabTwoLayout.newTab().setText("TWO"))
        binding.tabTwoLayout.addTab(binding.tabTwoLayout.newTab().setText("THREE"))

        val layoutInflater = LayoutInflater.from(this)
        val adapter = ThreePageAdapter(layoutInflater)
        binding.viewTwoPager.adapter = adapter

        binding.tabTwoLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewTwoPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.viewTwoPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(binding.tabTwoLayout)
        )
    }
}

class ThreePageAdapter(
    val layoutInflater: LayoutInflater
) : PagerAdapter() {

    // 뷰를 그려주는 부분
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.fargment_first, container, false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.fargment_two, container, false)
                container.addView(view)
                return view
            }
            2 -> {
                val view = layoutInflater.inflate(R.layout.fargment_three, container, false)
                container.addView(view)
                return view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.fargment_first, container, false)
                container.addView(view)
                return view
            }
        }
    }

    // 페이저를 넘길 때 뷰가 사라질 때 뷰를 파기시키는 함수
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return 3
    }

    // 그려진 뷰가 내가 그린 뷰랑 같은지 확인하는 키워드
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }
}