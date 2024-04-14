package com.edufelip.catapp

import android.app.Application
import com.edufelip.catapp.data.service.RemoteConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

abstract class BaseApplication: Application()

@HiltAndroidApp
class CatApplication : BaseApplication() {

    @Inject
    lateinit var mRemoteConfig: RemoteConfig

    override fun onCreate() {
        super.onCreate()
        mRemoteConfig.init()
    }
}