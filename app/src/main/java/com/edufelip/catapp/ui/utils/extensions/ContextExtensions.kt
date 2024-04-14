package com.edufelip.catapp.ui.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.toastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}