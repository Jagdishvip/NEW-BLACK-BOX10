package com.vspace.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupUI()
        setupObservers()
        setupListeners()
    }

    abstract fun getViewBinding(): VB
    abstract fun setupUI()
    abstract fun setupObservers()
    abstract fun setupListeners()

    protected fun showMessage(message: String) {
        // Implementation for showing toast or snackbar
    }

    protected fun showError(error: String) {
        // Implementation for showing error
    }

    protected fun showSuccess(message: String) {
        // Implementation for showing success
    }
}
