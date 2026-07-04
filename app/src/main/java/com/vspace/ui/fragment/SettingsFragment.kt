package com.vspace.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vspace.databinding.FragmentSettingsBinding
import com.vspace.ui.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun setupUI() {
        // Setup UI
    }

    override fun setupObservers() {
        // Setup observers
    }

    override fun setupListeners() {
        binding.btnFeedback.setOnClickListener {
            // Handle feedback
        }
    }
}
