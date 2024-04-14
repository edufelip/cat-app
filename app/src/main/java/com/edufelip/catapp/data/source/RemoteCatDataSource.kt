package com.edufelip.catapp.data.source

import com.edufelip.catapp.data.model.CatResponse
import com.edufelip.catapp.data.service.CatApi
import com.edufelip.catapp.data.service.RemoteConfig
import com.edufelip.catapp.data.utils.safeApiCall
import javax.inject.Inject

class RemoteCatDataSource @Inject constructor(
    private val api: CatApi,
    private val remoteConfig: RemoteConfig
) {

    suspend fun getCatDetail(id: String): CatResponse {
        return safeApiCall {
            api.getCatDetail(id)
        }
    }

    fun getCatList(): List<CatResponse> {
        return remoteConfig.getCatList()
    }
}