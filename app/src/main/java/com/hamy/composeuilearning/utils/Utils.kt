package com.hamy.composeuilearning.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun myLogs(message: String) = Log.d("myLog",message)

fun Context.myToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()