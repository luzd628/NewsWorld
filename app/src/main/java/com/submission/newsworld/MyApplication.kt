package com.submission.newsworld

import android.app.Application
import com.submission.newsworld.core.di.databaseModule
import com.submission.newsworld.core.di.networkModule
import com.submission.newsworld.core.di.repositoryModule
import com.submission.newsworld.di.useCaseModule
import com.submission.newsworld.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

    }

}