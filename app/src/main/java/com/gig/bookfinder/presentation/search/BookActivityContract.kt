package com.gig.bookfinder.presentation.search

import com.gig.bookfinder.domain.model.BookItem
import com.gig.bookfinder.presentation.base.BasePresenter

interface BookActivityContract {
    interface Presenter : BasePresenter<BookActivityContract.MainView> {
        fun isDownloading(): Boolean
        fun downloadBooks(searchRequest: String?)
        fun paginatingBooks(startIndex: String)
    }

    interface MainView {
        fun showBooks(books: List<BookItem>)
        fun addNewBooks(books: List<BookItem>)
        fun showError(message: String)
        fun setVisibilityProgress(isVisible: Boolean)
    }
}