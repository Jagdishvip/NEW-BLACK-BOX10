package com.vspace.ui.widgets

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import com.vspace.databinding.WidgetAppCardBinding

class AppCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialCardView(context, attrs, defStyle) {
    private val binding = WidgetAppCardBinding.inflate(LayoutInflater.from(context), this)

    fun setAppInfo(appInfo: ApplicationInfo, appName: String, packageManager: android.content.pm.PackageManager) {
        binding.apply {
            tvAppName.text = appName
            tvPackageName.text = appInfo.packageName
            ivAppIcon.setImageDrawable(packageManager.getApplicationIcon(appInfo))
        }
    }

    fun setOnSelectListener(listener: OnClickListener) {
        setOnClickListener(listener)
    }
}
