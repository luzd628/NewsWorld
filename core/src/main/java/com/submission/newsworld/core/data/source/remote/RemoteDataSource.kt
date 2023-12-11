package com.submission.newsworld.core.data.source.remote

import android.util.Log
import com.submission.newsworld.core.data.source.remote.network.ApiResponse
import com.submission.newsworld.core.data.source.remote.network.ApiService
import com.submission.newsworld.core.data.source.remote.response.PostsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){


    fun getAllNews(): Flow<ApiResponse<List<PostsItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListNews()
                val dataArray = response.data.posts
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data.posts))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}