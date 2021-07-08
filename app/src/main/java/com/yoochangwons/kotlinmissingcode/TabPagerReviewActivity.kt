package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.yoochangwons.kotlinmissingcode.databinding.ActivityTabPagerReviewBinding

class TabPagerReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabPagerReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tabLayoutReview.addTab(binding.tabLayoutReview.newTab().setText("ONE"))
        binding.tabLayoutReview.addTab(binding.tabLayoutReview.newTab().setText("TWO"))

        val adapter = FragmentReviewAdapter(supportFragmentManager, 2)
        binding.viewPagerReview.adapter = adapter

        binding.tabLayoutReview.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPagerReview.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}

class FragmentReviewAdapter(
    private val fragmentManager: FragmentManager,
    val tabCount : Int
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FragmentFirstReview()
            }
            1 -> {
                return FragmentSecondReview()
            }
            else -> return FragmentFirstReview()
        }
    }
}
