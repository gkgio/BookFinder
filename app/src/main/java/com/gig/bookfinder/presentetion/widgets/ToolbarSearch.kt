package com.gig.bookfinder.presentetion.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import com.gig.bookfinder.R

class ToolbarSearch : FrameLayout {
    lateinit var clearBtnContainer: FrameLayout
    lateinit var searchEditText: EditText

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attributes: AttributeSet?) {
        inflate(context, R.layout.view_search_toolbar, this)
        clearBtnContainer = findViewById(R.id.clearBtnContainer)
        searchEditText = findViewById(R.id.searchEditText)
    }
}
