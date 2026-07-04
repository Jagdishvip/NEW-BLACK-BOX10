package com.vspace.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vspace.databinding.FragmentAppsBinding
import com.vspace.ui.base.BaseFragment

class AppsFragment : BaseFragment<FragmentAppsBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAppsBinding.inflate(inflater, container, false)

    override fun setupUI() {
        // Setup UI
    }

    override fun setupObservers() {
        // Setup observers
    }

    override fun setupListeners() {
        // Setup listeners
    }
}
