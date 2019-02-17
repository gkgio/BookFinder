package com.gig.bookfinder.presentetion.di

import android.content.Context
import com.gig.bookfinder.BookFinderApp
import com.gig.bookfinder.BuildConfig
import com.gig.bookfinder.data.network.IService
import com.gig.bookfinder.data.repository.BookRepositoryImpl
import com.gig.bookfinder.domain.repository.BookRepository
import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.interactors.PaginatingUseCase
import com.gig.bookfinder.domain.interactors.executers.DownloadListOfBooksUseCaseImpl
import com.gig.bookfinder.domain.interactors.executers.PaginatingUseCaseImpl
import com.gig.bookfinder.presentetion.search.BookActivityContract
import com.gig.bookfinder.presentetion.search.BookPresenter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val application: BookFinderApp) {
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @ActivityScope
    fun provideHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @ActivityScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .followSslRedirects(true)
            .build()
    }

    @Provides
    @ActivityScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @ActivityScope
    fun provideRestService(retrofit: Retrofit): IService = retrofit.create(IService::class.java)

    @Provides
    @ActivityScope
    fun provideBookRepositoryImpl(api: IService): BookRepositoryImpl {
        return BookRepositoryImpl(api)
    }

    @Provides
    @ActivityScope
    fun provideBookRepository(repository: BookRepositoryImpl): BookRepository {
        return repository
    }

    @Provides
    @ActivityScope
    internal fun provideDownLoadUseCase(repository: BookRepository): DownloadListOfBooksUseCase {
        return DownloadListOfBooksUseCaseImpl(repository)
    }

    @Provides
    @ActivityScope
    internal fun providePagingUseCase(repository: BookRepository): PaginatingUseCase {
        return PaginatingUseCaseImpl(repository)
    }

    @ActivityScope
    @Provides
    fun provideBookSearchPresenter(
        useCase: DownloadListOfBooksUseCase,
        pagUseCase: PaginatingUseCase
    ): BookActivityContract.Presenter {
        return BookPresenter(useCase, pagUseCase)
    }

}