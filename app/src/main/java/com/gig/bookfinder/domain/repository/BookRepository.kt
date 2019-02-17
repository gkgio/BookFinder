package com.gig.bookfinder.domain.repository

import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.interactors.PaginatingUseCase

interface BookRepository {
    fun setDownloadListListener(listener: DownloadListOfBooksUseCase.ResponseListener)
    fun downloadBooks(searchRequest: String)

    fun setPaginatingListener(listener: PaginatingUseCase.CallbackListener)
    fun paginating(startIndex: String)
}