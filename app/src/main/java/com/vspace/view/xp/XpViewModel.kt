package com.vspace.view.xp

import androidx.lifecycle.MutableLiveData
import com.vspace.bean.XpModuleInfo
import com.vspace.data.XpRepository
import com.vspace.view.base.BaseViewModel

class XpViewModel(private val repo: XpRepository) : BaseViewModel() {
    val appsLiveData = MutableLiveData<List<XpModuleInfo>>()
    val resultLiveData = MutableLiveData<String>()

    fun getInstalledModule() {
        launchOnUI {
            repo.getInstallModules(appsLiveData)
        }
    }

    fun installModule(source:String) {
        launchOnUI {
            repo.installModule(source, resultLiveData)
        }
    }

    fun unInstallModule(packageName: String) {
        launchOnUI {
            repo.unInstallModule(packageName, resultLiveData)
        }
    }
}
