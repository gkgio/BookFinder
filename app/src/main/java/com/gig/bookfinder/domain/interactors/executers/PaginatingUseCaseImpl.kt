package com.gig.bookfinder.domain.interactors.executers

import com.gig.bookfinder.domain.interactors.PaginatingUseCase
import com.gig.bookfinder.domain.repository.BookRepository

class PaginatingUseCaseImpl(private val repository: BookRepository) : PaginatingUseCase {

    override fun execute(startIndex: String) {
        repository.paginating(startIndex)
    }

    override fun setListener(listener: PaginatingUseCase.CallbackListener) {
        repository.setPaginatingListener(listener)
    }
}
