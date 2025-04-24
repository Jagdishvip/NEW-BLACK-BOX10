package com.vspace.view.list

import androidx.lifecycle.MutableLiveData
import com.vspace.bean.InstalledAppBean
import com.vspace.data.AppsRepository
import com.vspace.view.base.BaseViewModel

class ListViewModel(private val repo: AppsRepository) : BaseViewModel() {
    val appsLiveData = MutableLiveData<List<InstalledAppBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun previewInstalledList() {
        launchOnUI{
            repo.previewInstallList()
        }
    }

    fun getInstallAppList(userID: Int) {
        launchOnUI {
            repo.getInstalledAppList(userID, loadingLiveData, appsLiveData)
        }
    }

    fun getInstalledModules() {
        launchOnUI {
            repo.getInstalledModuleList(loadingLiveData, appsLiveData)
        }
    }
}
