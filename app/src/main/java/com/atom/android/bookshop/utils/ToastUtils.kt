package com.atom.android.bookshop.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resource: Int) {
    Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
}
