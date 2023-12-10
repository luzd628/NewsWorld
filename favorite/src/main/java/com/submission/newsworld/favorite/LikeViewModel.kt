package com.submission.newsworld.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.newsworld.core.domain.usecase.NewsUsecase

class LikeViewModel(newsUseCase: NewsUsecase) : ViewModel() {

    val favoriteNews = newsUseCase.getFavoriteNews().asLiveData()

}