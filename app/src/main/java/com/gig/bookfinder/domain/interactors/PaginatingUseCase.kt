package com.gig.bookfinder.domain.interactors

import com.gig.bookfinder.domain.model.BookItem

interface PaginatingUseCase {

    interface CallbackListener {
        fun addNewBooks(books: List<BookItem>)
        fun showErrorPaginationBook(message: String)
    }

    fun execute(startIndex: String)
    fun setListener(listener: CallbackListener)
}
