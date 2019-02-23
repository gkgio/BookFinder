package com.gig.bookfinder.presentetion.search

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.gig.bookfinder.BookFinderApp
import com.gig.bookfinder.R
import com.gig.bookfinder.domain.model.BookItem
import com.gig.bookfinder.presentetion.showErrorAlertDialog
import com.gig.bookfinder.presentetion.widgets.ToolbarSearch
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.view.MotionEvent
import android.widget.EditText
import com.gig.bookfinder.presentetion.hideKeyboard


class BookActivity : AppCompatActivity(), BookActivityContract.MainView {

    @Inject
    lateinit var presenter: BookActivityContract.Presenter

    companion object {
        private const val QUERY = "QUERY"
    }

    private var bookListAdapter: BookListAdapter? = null

    lateinit var recyclerBooks: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var toolbarSearch: ToolbarSearch

    private var lastQuery: String? = null

    private val compositeDisposable = CompositeDisposable()

    private fun initView() {
        recyclerBooks = findViewById(R.id.recyclerBooks)
        progressBar = findViewById(R.id.progress_bar)
        toolbarSearch = findViewById(R.id.toolbarSearch)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(QUERY, lastQuery)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        BookFinderApp.appComponent.injectBookActivity(this)
        presenter.attachView(this)

        initView()

        val layoutManager = LinearLayoutManager(this)
        bookListAdapter = BookListAdapter()
        recyclerBooks.layoutManager = layoutManager
        recyclerBooks.adapter = bookListAdapter
        recyclerBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItemPosition + visibleCount >= totalItemCount && totalItemCount > 3) {
                    if (!presenter.isDownloading())
                        presenter.paginatingBooks(Integer.toString(totalItemCount + 1))
                }
            }
        })

        compositeDisposable.add(RxTextView.textChanges(toolbarSearch.searchEditText)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe {
                if (it.length > 2) {
                    presenter.downloadBooks(it.toString())
                }
            })

        if (savedInstanceState != null) {
            lastQuery = savedInstanceState.getString(QUERY)
            presenter.downloadBooks(lastQuery)
        }
    }

    override fun showBooks(books: List<BookItem>) {
        bookListAdapter?.setBooksList(books)
    }

    override fun addNewBooks(books: List<BookItem>) {
        bookListAdapter?.addBooksList(books)
    }

    override fun showError(message: String) {
        showErrorAlertDialog(
            this,
            message,
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        compositeDisposable.dispose()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val view = currentFocus

        if (view is EditText) {
            val focus = currentFocus
            if (focus != null) {
                val scrcoords = IntArray(2)
                focus.getLocationOnScreen(scrcoords)
                val x = event.rawX + focus.left - scrcoords[0]
                val y = event.rawY + focus.top - scrcoords[1]

                if (event.action == MotionEvent.ACTION_UP && (x < focus.left || x >= focus.right
                            || y < focus.top || y > focus.bottom)) {
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
