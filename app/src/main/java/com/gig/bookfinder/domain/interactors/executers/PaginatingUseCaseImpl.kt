package com.gig.bookfinder.domain.interactors.executers

import googlebooks.com.domain.interactors.PaginingUseCase
import googlebooks.com.domain.repository.Repository

class PaginigUseCaseImpl(private val repository: Repository) : PaginingUseCase {
    fun execute(startIndex: String) {
        repository.pagining(startIndex)
    }

    fun setListener(listener: CallbackListener) {
        repository.setPaginingListener(listener)
    }
}
