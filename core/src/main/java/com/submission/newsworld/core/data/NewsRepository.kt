package com.submission.newsworld.core.data

import com.submission.newsworld.core.data.source.local.LocalDataSource
import com.submission.newsworld.core.data.source.remote.RemoteDataSource
import com.submission.newsworld.core.data.source.remote.network.ApiResponse
import com.submission.newsworld.core.data.source.remote.response.PostsItem
import com.submission.newsworld.core.domain.model.News
import com.submission.newsworld.core.domain.repository.INewsRepository
import com.submission.newsworld.core.utils.AppExecutors
import com.submission.newsworld.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): INewsRepository {


    override fun getAllNews(): Flow<Resource<List<News>>> =

        object : NetworkBoundResource<List<News>, List<PostsItem>>(appExecutors){
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PostsItem>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<PostsItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }

        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity,state) }
    }


}