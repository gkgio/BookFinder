package com.gig.bookfinder.presentetion.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gig.bookfinder.BookFinderApp
import com.gig.bookfinder.R
import com.gig.bookfinder.domain.model.BookItem
import javax.inject.Inject

class BookActivity : AppCompatActivity(), BookActivityContract.MainView {

    @Inject
    lateinit var presenter: BookActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        BookFinderApp.appComponent.injectBookActivity(this)
    }

    override fun showBooks(books: List<BookItem>) {

    }

    override fun addNewBooks(books: List<BookItem>) {

    }

    override fun showError(message: String) {

    }
}
