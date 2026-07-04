package com.vspace.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.vspace.databinding.DialogInjectionSuccessBinding

class InjectionSuccessDialog(
    context: Context,
    private val appName: String,
    private val libraryName: String,
    private val onLaunchClick: () -> Unit,
    private val onDoneClick: () -> Unit
) : Dialog(context) {
    private lateinit var binding: DialogInjectionSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = DialogInjectionSuccessBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setAttributes(WindowManager.LayoutParams().apply {
                flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                dimAmount = 0.5f
            })
        }

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        binding.apply {
            textAppName.text = appName
            textLibrary.text = libraryName
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnLaunch.setOnClickListener {
                onLaunchClick()
                dismiss()
            }
            btnDone.setOnClickListener {
                onDoneClick()
                dismiss()
            }
        }
    }
}
