package com.vspace.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vspace.databinding.FragmentHomeModernBinding
import com.vspace.ui.base.BaseFragment
import com.vspace.ui.viewmodel.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeModernBinding>() {
    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeModernBinding.inflate(inflater, container, false)

    override fun setupUI() {
        binding.apply {
            // Setup UI components
        }
    }

    override fun setupObservers() {
        viewModel.selectedApp.observe(viewLifecycleOwner) { app ->
            // Update UI with selected app
        }

        viewModel.selectedSoFile.observe(viewLifecycleOwner) { file ->
            // Update UI with selected SO file
        }

        viewModel.injectionProgress.observe(viewLifecycleOwner) { progress ->
            // Update progress bar
        }
    }

    override fun setupListeners() {
        binding.btnSelectApp.setOnClickListener {
            // Open app selection dialog
        }

        binding.btnSelectSo.setOnClickListener {
            // Open file selector for .SO files
        }
    }
}
