package com.edufelip.catapp.data.service

import android.util.Log
import com.edufelip.catapp.R
import com.edufelip.catapp.data.model.CatResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfig @Inject constructor() {

    private companion object {
        const val FETCH_INTERVAL_IN_SECONDS = 10L
        const val KEY_CAT_LIST = "cat_list"
    }

    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    fun init() {
        remoteConfig.setConfigSettingsAsync(
            FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(FETCH_INTERVAL_IN_SECONDS)
                .build()
        )

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            Log.i("Remote config", "Fetch successful")
        }.addOnFailureListener {
            Log.i("Remote config", "Task fetch failed")
        }
    }

    fun getCatList(): List<CatResponse> {
        val stringJson = remoteConfig.getString(KEY_CAT_LIST)
        return if (stringJson.isNotEmpty()) {
            Gson().fromJson(stringJson, Array<CatResponse>::class.java).toList()
        } else{
            emptyList()
        }
    }
}