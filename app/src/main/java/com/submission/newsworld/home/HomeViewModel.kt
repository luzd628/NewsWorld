package com.submission.newsworld.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData


class HomeViewModel(newsUseCase: com.submission.newsworld.core.domain.usecase.NewsUsecase) : ViewModel(){

    val news = newsUseCase.getAllNews().asLiveData()

}