package com.submission.newsworld.core.domain.usecase

import com.submission.newsworld.core.domain.model.News
import com.submission.newsworld.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository): NewsUsecase {

    override fun getAllNews() = newsRepository.getAllNews()

    override fun getFavoriteNews() = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, state: Boolean) = newsRepository.setFavoriteNews(news,state)
}