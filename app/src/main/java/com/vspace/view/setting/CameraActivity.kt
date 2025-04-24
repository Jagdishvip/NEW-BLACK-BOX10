package com.vspace.view.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.*
import com.vspace.R
import com.vspace.camera.MultiPreferences
import com.vspace.settings.MethodType
import com.vspace.util.AppUtil
import com.vspace.util.ToastUtils

class CameraActivity : BaseFragment() {

    private lateinit var btnProtectMethod: AppCompatButton
    private lateinit var btnSave: AppCompatButton
    private lateinit var tvProtectMethodText: AppCompatTextView
    private lateinit var tvTip: AppCompatTextView
    private lateinit var tvAudioText: AppCompatTextView
    private lateinit var etInput: AppCompatEditText
    private lateinit var switchAudio: SwitchCompat
    private lateinit var btnChooseVideo: AppCompatButton

    private var selectedUri: Uri? = null
    private var methodType: Int = MethodType.TYPE_DISABLE_CAMERA

    private val openDocumentResult = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let {
            selectedUri = it
            requireContext().contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            etInput.setText(it.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.activity_camera, container, false)
        initView(view)
        return view
    }

    private fun initView(rootView: View) {
        btnProtectMethod = rootView.findViewById(R.id.protect_method_btn)
        btnSave = rootView.findViewById(R.id.protect_save)
        tvProtectMethodText = rootView.findViewById(R.id.protect_method_text)
        tvTip = rootView.findViewById(R.id.protect_tip)
        etInput = rootView.findViewById(R.id.protect_path)
        tvAudioText = rootView.findViewById(R.id.protect_audio)
        switchAudio = rootView.findViewById(R.id.protect_audio_switch)
        btnChooseVideo = rootView.findViewById(R.id.protect_video_select)

        btnChooseVideo.setOnClickListener {
            openDocumentResult.launch(arrayOf("video/*"))
        }

        btnProtectMethod.setOnClickListener { view ->
            PopupMenu(requireContext(), view).apply {
                inflate(R.menu.camera_menu)
                setOnMenuItemClickListener { item: MenuItem ->
                    methodType = when (item.itemId) {
                        R.id.protect_method_disable_camera -> MethodType.TYPE_DISABLE_CAMERA
                        R.id.protect_method_local -> MethodType.TYPE_LOCAL_VIDEO
                        R.id.protect_method_network -> MethodType.TYPE_NETWORK_VIDEO
                        else -> MethodType.TYPE_DISABLE_CAMERA
                    }
                    updateUI(methodType)
                    true
                }
                show()
            }
        }

        btnSave.setOnClickListener { saveSettings() }

        methodType = MultiPreferences.getInstance().getInt("method_type", MethodType.TYPE_DISABLE_CAMERA)
        updateUI(methodType)
    }

    private fun updateUI(type: Int) {
        when (type) {
            MethodType.TYPE_DISABLE_CAMERA -> {
                etInput.visibility = View.GONE
                btnChooseVideo.visibility = View.GONE
                tvAudioText.visibility = View.GONE
                switchAudio.visibility = View.GONE
            }
            MethodType.TYPE_LOCAL_VIDEO -> {
                etInput.visibility = View.VISIBLE
                etInput.isEnabled = false
                btnChooseVideo.visibility = View.VISIBLE
                tvAudioText.visibility = View.VISIBLE
                switchAudio.visibility = View.VISIBLE
            }
            MethodType.TYPE_NETWORK_VIDEO -> {
                etInput.visibility = View.VISIBLE
                etInput.isEnabled = true
                btnChooseVideo.visibility = View.GONE
                tvAudioText.visibility = View.VISIBLE
                switchAudio.visibility = View.VISIBLE
            }
        }
    }

    private fun saveSettings() {
        AppUtil.killAllApps()
        MultiPreferences.getInstance().setInt("method_type", methodType)

        when (methodType) {
            MethodType.TYPE_DISABLE_CAMERA -> ToastUtils.showToast("Camera Disabled")
            MethodType.TYPE_LOCAL_VIDEO -> {
                MultiPreferences.getInstance().setString("video_path_local", selectedUri.toString())
                ToastUtils.showToast("Local video saved")
            }
            MethodType.TYPE_NETWORK_VIDEO -> {
                val url = etInput.text.toString()
                if (TextUtils.isEmpty(url) || !url.startsWith("http")) {
                    ToastUtils.showToast("Invalid URL")
                    return
                }
                MultiPreferences.getInstance().setString("video_path_network", url)
                ToastUtils.showToast("Network video saved")
            }
        }
    }
}
