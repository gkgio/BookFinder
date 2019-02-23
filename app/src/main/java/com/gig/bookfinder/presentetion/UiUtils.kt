package com.gig.bookfinder.presentetion

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.gig.bookfinder.R

fun showErrorAlertDialog(
    activity: Activity?,
    message: String,
    positiveCallback: DialogInterface.OnClickListener
) {
    if (activity != null) {
        android.support.v7.app.AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton(R.string.yes, positiveCallback)
            .create()
            .show()
    } else {
        Log.e(TAG, "Can't show dialog without activity")
    }
}

fun Activity?.hideKeyboard() {
    val view = this?.currentFocus
    if (view != null) {
        val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}