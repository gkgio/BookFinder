package com.gig.bookfinder.domain.interactors.executers

import com.gig.bookfinder.domain.interactors.DownloadListOfBooksUseCase
import com.gig.bookfinder.domain.repository.BookRepository


class DownloadListOfBooksUseCaseImpl(private val repository: BookRepository) : DownloadListOfBooksUseCase {

    override fun execute(searchRequest: String) {
        repository.downloadBooks(searchRequest)
    }

    override fun setListener(listener: DownloadListOfBooksUseCase.ResponseListener) {
        repository.setDownloadListListener(listener)
    }
}
