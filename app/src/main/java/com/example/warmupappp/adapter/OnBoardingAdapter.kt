package com.example.warmupappp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.warmupappp.view.OnboardingFragment1
import com.example.warmupappp.view.OnboardingFragment2
import com.example.warmupappp.view.ViewPagerFragment

class OnboardingAdapter(viewPagerFragment: ViewPagerFragment) : FragmentStateAdapter(viewPagerFragment) {
    private val fragmentList = listOf(
        OnboardingFragment1(),
        OnboardingFragment2()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}