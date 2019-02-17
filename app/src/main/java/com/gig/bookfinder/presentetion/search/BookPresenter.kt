package com.gig.bookfinder.presentetion.search

import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.interactors.PaginatingUseCase
import com.gig.bookfinder.domain.model.BookItem
import javax.inject.Inject

class BookPresenter() :
    BookActivityContract.Presenter,
    DownloadListOfBooksUseCase.ResponseListener,
    PaginatingUseCase.CallbackListener {

    private var view: BookActivityContract.MainView? = null
    private var isDownloading: Boolean = false

    lateinit var useCase: DownloadListOfBooksUseCase
    lateinit var pagUseCase: PaginatingUseCase

    @Inject
    constructor(
        useCase: DownloadListOfBooksUseCase,
        pagUseCase: PaginatingUseCase
    ) : this() {
        this.useCase = useCase
        this.pagUseCase = pagUseCase
        this.pagUseCase.setListener(this)
        this.useCase.setListener(this)
    }

    override fun attachView(view: BookActivityContract.MainView) {
        this.view = view
    }

    override fun downloadBooks(searchRequest: String) {
        useCase.execute(searchRequest)
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

    override fun showErrorDownloadBooks(message: String) {
        view?.showError(message)
    }

    override fun showErrorPagginationBook(message: String) {
        view?.showError(message)
    }

    override fun detachView() {
        view = null
    }
}