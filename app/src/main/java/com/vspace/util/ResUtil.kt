package com.vspace.util

import androidx.annotation.StringRes
import com.vspace.app.App

object ResUtil {
    fun getString(@StringRes id: Int, vararg arg: String): String {
        if (arg.isEmpty()) {
            return App.getContext().getString(id)
        }
        return App.getContext().getString(id, *arg)
    }
}
