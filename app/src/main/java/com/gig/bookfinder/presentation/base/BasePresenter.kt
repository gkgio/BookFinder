package com.gig.bookfinder.presentation.base

interface BasePresenter<V> {
    fun attachView(view: V)
    fun detachView()
}
