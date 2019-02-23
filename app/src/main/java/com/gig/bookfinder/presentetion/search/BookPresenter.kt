package com.gig.bookfinder.presentetion.search

import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.interactors.ErrorOrProgressUseCase
import com.gig.bookfinder.domain.interactors.PaginatingUseCase
import com.gig.bookfinder.domain.model.BookItem
import javax.inject.Inject

class BookPresenter() :
    BookActivityContract.Presenter,
    DownloadListOfBooksUseCase.ResponseListener,
    PaginatingUseCase.CallbackListener,
    ErrorOrProgressUseCase.CallbackListener {

    private var view: BookActivityContract.MainView? = null
    private var isDownloading: Boolean = false

    private lateinit var downLoaduseCase: DownloadListOfBooksUseCase
    private lateinit var pagUseCase: PaginatingUseCase
    private lateinit var errorOrProgressUseCase: ErrorOrProgressUseCase

    @Inject
    constructor(
        useCase: DownloadListOfBooksUseCase,
        pagUseCase: PaginatingUseCase,
        errorOrProgressUseCase: ErrorOrProgressUseCase
    ) : this() {
        this.downLoaduseCase = useCase
        this.pagUseCase = pagUseCase
        this.errorOrProgressUseCase = errorOrProgressUseCase
        this.pagUseCase.setListener(this)
        this.downLoaduseCase.setListener(this)
        this.errorOrProgressUseCase.setListener(this)
    }

    override fun attachView(view: BookActivityContract.MainView) {
        this.view = view
    }

    override fun downloadBooks(searchRequest: String?) {
        if (searchRequest != null) {
            downLoaduseCase.execute(searchRequest)
        }
    }

    override fun paginatingBooks(startIndex: String) {
        isDownloading = true
        pagUseCase.execute(startIndex)
    }

    override fun isDownloading(): Boolean {
        return isDownloading
    }

    override fun showBooks(books: List<BookItem>) {
        view?.showBooks(books)
    }

    override fun addNewBooks(books: List<BookItem>) {
        isDownloading = false
        view?.addNewBooks(books)
    }

    override fun setVisibilityProgress(isVisible: Boolean) {
        view?.setVisibilityProgress(isVisible)
    }

    override fun showError(message: String) {
        view?.showError(message)
    }

    override fun detachView() {
        view = null
    }
}