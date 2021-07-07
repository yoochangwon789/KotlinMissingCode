package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yoochangwons.kotlinmissingcode.databinding.ActivityTabPagerBinding

class TabPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ONE"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TWO"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Three"))

        val pagerAdapter = PagerAdapter(supportFragmentManager, 3)
        binding.viewPager.adapter = pagerAdapter
    }
}

class PagerAdapter(
    fragmentManager: FragmentManager,
    private val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Fragment1()
            }
            1 -> {
                return Fragment2()
            }
            2 -> {
                return Fragment3()
            }
            else -> return Fragment1()
        }
    }
}
