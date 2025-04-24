package com.vspace.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.vspace.app.App
import com.vspace.util.ResUtil.getString

object ToastEx {
    private var toastImpl:Toast? = null

    fun Context.toast(msg:String) {
        toastImpl?.cancel()
        toastImpl = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toastImpl?.show()
    }

    fun toast(msg: String) {
        App.getContext().toast(msg)
    }

    fun toast(@StringRes msgID:Int) {
        toast(getString(msgID))
    }
}
