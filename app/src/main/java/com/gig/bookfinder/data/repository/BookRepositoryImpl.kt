package com.gig.bookfinder.data.repository

import android.annotation.SuppressLint
import com.gig.bookfinder.data.network.IService
import com.gig.bookfinder.data.network.REST
import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.interactors.PaginatingUseCase
import com.gig.bookfinder.domain.model.BookItem
import com.gig.bookfinder.domain.repository.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookRepositoryImpl(private var service: IService) : BookRepository {

    private var listListener: DownloadListOfBooksUseCase.ResponseListener? = null
    private var pagListener: PaginatingUseCase.CallbackListener? = null

    private var cache: ArrayList<BookItem>? = null
    private var lastRequest: String? = null


    override fun setDownloadListListener(listener: DownloadListOfBooksUseCase.ResponseListener) {
        this.listListener = listener
    }

    @SuppressLint("CheckResult")
    override fun downloadBooks(searchRequest: String) {
        val cache = cache
        if (searchRequest == lastRequest && cache != null) {
            listListener?.showBooks(cache)
        } else {
            lastRequest = searchRequest
            service.getRates(searchRequest, "10")
                .subscribeOn(Schedulers.io())
                .compose(REST.instance.errorHandler.cast())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    val listBooks = items.items
                    if (listBooks != null) {
                        listListener?.showBooks(listBooks)
                    }
                }) { throwable ->
                    listListener?.showErrorDownloadBooks(throwable.message ?: "")
                }
        }
    }

    override fun setPaginatingListener(listener: PaginatingUseCase.CallbackListener) {
        pagListener = listener
    }

    @SuppressLint("CheckResult")
    override fun paginating(startIndex: String) {
        val lastRequest = lastRequest
        if (lastRequest != null) {
            service.paginating(lastRequest, startIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { items ->
                    val listBooks = items.items
                    if (listBooks != null) {
                        cache?.addAll(items.items)
                    }
                }
                .subscribe({ items ->
                    val listBooks = items.items
                    if (listBooks != null) {
                        pagListener?.addNewBooks(listBooks)
                    }
                }) { throwable ->
                    pagListener?.showErrorPagginationBook(throwable.message ?: "")
                }
        }
    }
}