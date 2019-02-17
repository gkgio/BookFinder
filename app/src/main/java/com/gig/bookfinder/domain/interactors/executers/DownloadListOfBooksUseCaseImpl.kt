package com.gig.bookfinder.domain.interactors.executers

import googlebooks.com.domain.interactors.DownloadListOfBooksUseCase
import googlebooks.com.domain.repository.Repository

class DownloadListOfBooksUseCaseImpl(private val repository: Repository) : DownloadListOfBooksUseCase {

    fun execute(searchRequest: String) {
        repository.downloadBooks(searchRequest)
    }

    fun setListener(listener: ResponseListener) {
        repository.setDownloadListListener(listener)
    }
}
