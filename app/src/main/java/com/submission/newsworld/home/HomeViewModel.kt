package com.submission.newsworld.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.newsworld.core.domain.usecase.NewsUsecase

class HomeViewModel(newsUseCase: com.submission.newsworld.core.domain.usecase.NewsUsecase) : ViewModel(){

    val news = newsUseCase.getAllNews().asLiveData()

}