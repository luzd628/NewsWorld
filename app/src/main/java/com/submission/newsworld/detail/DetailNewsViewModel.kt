package com.submission.newsworld.detail

import androidx.lifecycle.ViewModel
import com.submission.newsworld.core.domain.model.News
import com.submission.newsworld.core.domain.usecase.NewsUsecase

class DetailNewsViewModel(private val newsUseCase: NewsUsecase): ViewModel() {

    fun setFavoriteNews(news: News, newsStatus:Boolean)=
        newsUseCase.setFavoriteNews(news,newsStatus)

}