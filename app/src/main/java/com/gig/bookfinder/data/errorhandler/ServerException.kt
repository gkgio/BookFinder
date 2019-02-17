package com.gig.bookfinder.data.errorhandler


class ServerException internal constructor(val httpCode: Int, message: String,
                                           val code: Int) : RuntimeException(message)