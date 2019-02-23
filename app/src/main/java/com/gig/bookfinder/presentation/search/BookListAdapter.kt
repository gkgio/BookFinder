package com.gig.bookfinder.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gig.bookfinder.R
import com.gig.bookfinder.domain.model.BookItem

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.BookItemViewHolder>() {
    private var listBooks = arrayListOf<BookItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.BookItemViewHolder {
        return BookItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookListAdapter.BookItemViewHolder, position: Int) {
        val book = listBooks[position]
        val context = holder.itemView.context
        holder.tvTitle.text = context.getString(R.string.book_title_filter, book.volumeInfo?.title)
        if (book.volumeInfo?.authors?.isNotEmpty() == true) {
            holder.tvAuthor.text = context.getString(R.string.book_author_filter, book.volumeInfo.authors[0])
        } else {
            holder.tvAuthor.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    fun setBooksList(listBooks: List<BookItem>) {
        this.listBooks = listBooks as ArrayList<BookItem>
        notifyDataSetChanged()
    }

    fun addBooksList(listBooks: List<BookItem>) {
        this.listBooks.addAll(listBooks)
        notifyDataSetChanged()
    }

    // view holder class ======================
    class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
    }
}