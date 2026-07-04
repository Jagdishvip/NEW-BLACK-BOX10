package com.vspace.ui.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.vspace.R

class CustomProgressDialog(context: Context) : Dialog(context, R.style.Theme_BlackBox) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        val progressIndicator = CircularProgressIndicator(context).apply {
            indeterminateAnimationType = CircularProgressIndicator.INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS
        }
        setContentView(progressIndicator)
    }
}
