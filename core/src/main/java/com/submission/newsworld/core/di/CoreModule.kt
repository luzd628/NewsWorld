package com.submission.newsworld.core.di


import androidx.room.Room
import com.submission.newsworld.core.data.NewsRepository
import com.submission.newsworld.core.data.source.local.LocalDataSource
import com.submission.newsworld.core.data.source.local.room.NewsDatabase
import com.submission.newsworld.core.data.source.remote.RemoteDataSource
import com.submission.newsworld.core.data.source.remote.network.ApiService
import com.submission.newsworld.core.domain.repository.INewsRepository
import com.submission.newsworld.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        val passphrase:ByteArray = SQLiteDatabase.getBytes("berita".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "News.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api-berita-indonesia.vercel.app"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname,"sha256/rmFkZFN0jeU2jXzl6l9PNX8hDLeWqF77lK77TvA2U8k=")
            .add(hostname,"sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname,"sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-berita-indonesia.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> { NewsRepository(get(), get(), get()) }
}