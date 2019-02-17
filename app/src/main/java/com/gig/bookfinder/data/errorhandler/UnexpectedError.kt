package com.gig.bookfinder.data.errorhandler


class UnexpectedError(message: String, cause: Throwable) : RuntimeException(message, cause)