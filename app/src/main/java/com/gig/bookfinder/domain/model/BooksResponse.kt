package com.gig.bookfinder.domain.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("kind")
    val kind: String? = null,

    @SerializedName("items")
    val items: List<BookItem>? = null,

    @SerializedName("totalItems")
    val totalItems: Long? = null
)