package com.app.bookassistant.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.log(message: String) {
    Log.d("TAG", message)
}

fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

fun logd(message: String) {
    Log.d("TAG", message)
}