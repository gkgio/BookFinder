package com.gig.bookfinder.data.errorhandler

import android.content.res.Resources
import android.support.annotation.IntDef
import com.gig.bookfinder.R
import com.gig.bookfinder.di.ActivityScope


import javax.inject.Inject

@ActivityScope
class ApiErrors @Inject
constructor(private val res: Resources) {

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(SERVER_CONNECT_TIMEOUT, NO_CONNECTION, UNEXPECTED_ERROR)
    annotation class ErrorCode

    companion object {
        const val SERVER_CONNECT_TIMEOUT = R.string.server_connect_timeout
        const val NO_CONNECTION = R.string.no_internet_connection
        const val UNEXPECTED_ERROR = R.string.unexpected_error
    }

    fun getMessage(@ErrorCode error: Int): String {
        return res.getString(error)
    }
}