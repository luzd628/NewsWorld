package com.submission.newsworld.core.utils

import com.submission.newsworld.core.data.source.local.entity.NewsEntity
import com.submission.newsworld.core.data.source.remote.response.PostsItem
import com.submission.newsworld.core.domain.model.News

object DataMapper {

    fun mapResponsesToEntities(input: List<PostsItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                newsId= it.newsId,
                thumbnail = it.thumbnail,
                link = it.link,
                description = it.description,
                title = it.title,
                pubDate = it.pubDate,
                isFavorite = false,
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                newsId = it.newsId,
                thumbnail = it.thumbnail,
                link = it.link,
                description = it.description,
                title = it.title,
                pubDate = it.pubDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntity(
        newsId = input.newsId,
        thumbnail = input.thumbnail,
        link = input.link,
        description = input.description,
        title = input.title,
        pubDate = input.pubDate,
        isFavorite = input.isFavorite
    )

}