package com.gig.bookfinder.domain.interactors.executers

import com.gig.bookfinder.domain.interactors.ErrorOrProgressUseCase
import com.gig.bookfinder.domain.repository.BookRepository

class ErrorOrProgressUseCaseImpl(private val repository: BookRepository) : ErrorOrProgressUseCase {

    override fun setListener(listener: ErrorOrProgressUseCase.CallbackListener) {
        repository.setErrorOrProgressListener(listener)
    }
}