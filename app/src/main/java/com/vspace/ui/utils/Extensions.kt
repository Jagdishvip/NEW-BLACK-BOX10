package com.vspace.ui.utils

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    action: String? = null,
    actionCallback: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(requireView(), message, duration)
    if (action != null && actionCallback != null) {
        snackbar.setAction(action) { actionCallback() }
    }
    snackbar.show()
}

fun Fragment.showError(message: String) {
    showSnackbar(message, Snackbar.LENGTH_LONG)
}

fun Fragment.showSuccess(message: String) {
    showSnackbar(message, Snackbar.LENGTH_SHORT)
}
