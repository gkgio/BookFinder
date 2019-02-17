package com.gig.bookfinder.domain.model

import com.google.gson.annotations.SerializedName

data class BookItem(

    @SerializedName("totalItems")
    val kind: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("etag")
    val etag: String? = null,

    @SerializedName("selfLink")
    val selfLink: String? = null,

    @SerializedName("volumeInfo")
    val volumeInfo: BookVolumeInfo? = null
)