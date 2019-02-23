package com.gig.bookfinder.domain.interactors

import com.gig.bookfinder.domain.model.BookItem


interface DownloadListOfBooksUseCase {

    interface ResponseListener {
        fun showBooks(books: List<BookItem>)
    }

    fun execute(searchRequest: String)
    fun setListener(listener: ResponseListener)
}
