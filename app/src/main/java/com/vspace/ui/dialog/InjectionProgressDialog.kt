package com.vspace.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.vspace.databinding.DialogInjectionProgressBinding

class InjectionProgressDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogInjectionProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = DialogInjectionProgressBinding.inflate(layoutInflater)
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

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    fun setProgress(progress: Int) {
        binding.apply {
            progressLinear.progress = progress
            progressCircular.progress = progress
            progressPercentage.text = "$progress%"
        }
    }

    fun setStatus(status: String) {
        binding.progressText.text = status
    }

    fun completeStep(stepNumber: Int) {
        binding.apply {
            when (stepNumber) {
                1 -> {
                    iconStep1.isEnabled = true
                    textStep1.alpha = 1.0f
                }
                2 -> {
                    iconStep2.isEnabled = true
                    textStep2.alpha = 1.0f
                }
                3 -> {
                    iconStep3.isEnabled = true
                    textStep3.alpha = 1.0f
                }
            }
        }
    }
}
