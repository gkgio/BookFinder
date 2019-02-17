package com.gig.bookfinder.data.errorhandler

class ConnectionException internal constructor(message: String, cause: Throwable)
    : RuntimeException(message, cause)