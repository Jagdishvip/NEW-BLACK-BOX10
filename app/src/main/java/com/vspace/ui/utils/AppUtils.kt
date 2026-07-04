package com.vspace.ui.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class AppUtils(private val context: Context) {
    private val packageManager = context.packageManager

    fun getInstalledApps(): List<ApplicationInfo> {
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { !isSystemApp(it) }
            .sortedBy { getAppName(it) }
    }

    fun getAppName(appInfo: ApplicationInfo): String {
        return try {
            packageManager.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            appInfo.packageName
        }
    }

    fun getAppIcon(appInfo: ApplicationInfo) = packageManager.getApplicationIcon(appInfo)

    private fun isSystemApp(appInfo: ApplicationInfo): Boolean {
        return appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
}
