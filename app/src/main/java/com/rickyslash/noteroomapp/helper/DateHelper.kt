package com.rickyslash.noteroomapp.helper

import java.text.SimpleDateFormat
import java.util.*

// object is a special type of class to define singleton object (can only have 1 instance)
// this object helps to get 'date'
object DateHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}