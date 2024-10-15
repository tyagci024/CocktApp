package com.example.warmupappp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.warmupappp.databinding.FragmentViewPagerBinding
import com.example.warmupappp.adapter.OnboardingAdapter
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager

        val adapter = OnboardingAdapter(this)
        viewPager.adapter = adapter

        // TabLayoutMediator'i kaldırdık çünkü artık kullanmıyoruz

        binding.btnLogin.setOnClickListener {
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToListFragment()
            findNavController().navigate(action)
        }

    }
}
