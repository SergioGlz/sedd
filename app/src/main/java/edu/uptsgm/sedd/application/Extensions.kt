package edu.uptsgm.sedd.application

import android.app.Activity
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.dateFormat(dateFormat: String) : String {
    SimpleDateFormat(dateFormat, Locale.US).apply {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this@dateFormat
        return format(calendar.time)
    }
}

fun Long.dateFormat() : String = this.dateFormat("dd-MMM-yyyy hh:mm:ss.SSS")

fun <T> Activity.startActivityAndClearStack(clazz: Class<T>) =
    startActivityAndClearStack(Intent(this, clazz))

fun Activity.startActivityAndClearStack(intent: Intent) {
    intent.apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }.also {
        startActivity(intent)
    }
}