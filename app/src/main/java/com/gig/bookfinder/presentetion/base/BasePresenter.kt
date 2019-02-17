package com.gig.bookfinder.presentetion.base

interface BasePresenter<V> {
    fun attachView(view: V)
    fun detachView()
}
