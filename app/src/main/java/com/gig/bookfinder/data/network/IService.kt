package com.gig.bookfinder.data.network

import com.gig.bookfinder.domain.model.BooksResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("books/v1/volumes")
    fun getRates(@Query("q") search: String, @Query("maxResults") maxResult: String): Observable<BooksResponse>

    @GET("books/v1/volumes")
    fun paginating(@Query("q") search: String, @Query("startIndex") startIndex: String): Observable<BooksResponse>
}