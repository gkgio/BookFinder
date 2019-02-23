package com.gig.bookfinder.domain.model

import com.google.gson.annotations.SerializedName

data class BookVolumeInfo(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("authors")
    val authors: List<String>? = null
)