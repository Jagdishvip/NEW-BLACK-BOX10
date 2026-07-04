package com.vspace.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationView
import com.vspace.R
import com.vspace.databinding.ActivityMainModernBinding
import com.vspace.ui.base.BaseActivity
import com.vspace.ui.fragment.HomeFragment
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainModernBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    override fun getViewBinding() = ActivityMainModernBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    override fun setupUI() {
        setSupportActionBar(binding.toolbar)
        loadFragment(HomeFragment())
    }

    override fun setupObservers() {
        // Setup observers
    }

    override fun setupListeners() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_apps -> {
                    // Load apps fragment
                    true
                }
                R.id.nav_history -> {
                    // Load history fragment
                    true
                }
                R.id.nav_settings -> {
                    // Load settings fragment
                    true
                }
                else -> false
            }
        }
    }

    private fun setupNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                loadFragment(HomeFragment())
                true
            }
            else -> false
        }
    }
}
