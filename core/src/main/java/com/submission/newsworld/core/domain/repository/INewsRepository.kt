package com.submission.newsworld.core.domain.repository

import com.submission.newsworld.core.data.Resource
import com.submission.newsworld.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllNews(): Flow<Resource<List<News>>>

    fun getFavoriteNews(): Flow<List<News>>

    fun setFavoriteNews(news: News, state: Boolean)

}