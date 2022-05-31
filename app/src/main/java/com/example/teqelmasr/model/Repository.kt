package com.example.teqelmasr.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.teqelmasr.db.LocalSource
import com.example.teqelmasr.network.RemoteSource
import retrofit2.Response

class Repository private constructor(
    var remoteSource: RemoteSource,
    //var localSource: LocalSource,
    var context: Context
) : RepositoryInterface {

    companion object{
        private var instance: Repository? = null
        fun getInstance(remoteSource: RemoteSource,
                        context: Context
        ): Repository{
            return instance?: Repository(
                remoteSource,context)
        }
    }

    override suspend fun fetchSpareParts() :  Response<ProductItem> {
        return remoteSource.fetchSpareParts()
    }


}