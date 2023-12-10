package com.submission.newsworld.di

import com.submission.newsworld.detail.DetailNewsViewModel
import com.submission.newsworld.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<com.submission.newsworld.core.domain.usecase.NewsUsecase> {
        com.submission.newsworld.core.domain.usecase.NewsInteractor(
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    //viewModel { FavoriteViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}