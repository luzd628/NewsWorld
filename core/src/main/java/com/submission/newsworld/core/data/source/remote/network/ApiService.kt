package com.submission.newsworld.core.data.source.remote.network

import com.submission.newsworld.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("okezone/terbaru")
    suspend fun getListNews(): NewsResponse

}