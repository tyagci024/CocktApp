package com.example.warmupappp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.warmupappp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OnboardingFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding2, container, false)
    }
}
