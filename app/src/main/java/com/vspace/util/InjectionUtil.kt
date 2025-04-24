package com.vspace.util

import com.vspace.data.AppsRepository
import com.vspace.data.FakeLocationRepository
import com.vspace.data.GmsRepository
import com.vspace.data.XpRepository
import com.vspace.view.apps.AppsFactory
import com.vspace.view.fake.FakeLocationFactory
import com.vspace.view.gms.GmsFactory
import com.vspace.view.list.ListFactory
import com.vspace.view.xp.XpFactory

object InjectionUtil {
    private val appsRepository = AppsRepository()
    private val xpRepository = XpRepository()
    private val gmsRepository = GmsRepository()
    private val fakeLocationRepository = FakeLocationRepository()

    fun getAppsFactory() : AppsFactory {
        return AppsFactory(appsRepository)
    }

    fun getListFactory(): ListFactory {
        return ListFactory(appsRepository)
    }

    fun getXpFactory():XpFactory{
        return XpFactory(xpRepository)
    }

    fun getGmsFactory():GmsFactory{
        return GmsFactory(gmsRepository)
    }

    fun getFakeLocationFactory():FakeLocationFactory{
        return FakeLocationFactory(fakeLocationRepository)
    }
}
