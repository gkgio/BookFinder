package com.gig.bookfinder.data.network

import com.gig.bookfinder.di.ActivityScope
import com.gig.bookfinder.data.Config
import com.gig.bookfinder.data.errorhandler.ErrorHandler
import com.gig.bookfinder.domain.model.BooksResponse
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScope
class REST @Inject
constructor(retrofit: Retrofit, val errorHandler: ErrorHandler) : IService {
    companion object {
        @JvmField
        val LOG_TAG: String = REST::class.java.toString()

        lateinit var instance: REST
    }

    private val service: IService = retrofit.create(IService::class.java)

    private fun <T> setup(): ObservableTransformer<T, T> {
        return setup(Config.TIMEOUT, Config.RETRIES)
    }

    private fun <T> setup(timeout: Int, retries: Int): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .timeout(timeout.toLong(), TimeUnit.SECONDS)
                .retry(retries.toLong())
        }
    }

    override fun getRates(search: String, maxResult: String): Observable<BooksResponse> {
        return service.getRates(search, maxResult).compose(this.setup())
    }

    override fun paginating(search: String, startIndex: String): Observable<BooksResponse> {
        return service.paginating(search, startIndex).compose(this.setup())
    }
}