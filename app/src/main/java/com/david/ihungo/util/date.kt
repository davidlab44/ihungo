package com.david.ihungo.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

val sdf = SimpleDateFormat("hh:mm:ss")
val currentDate = sdf.format(Date())
val date = currentDate.filter {it in '0'..'9'}

class Dates {
    fun geDateAsString():String {
        val sdf = SimpleDateFormat("mm hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate.filter {it in '0'..'9'}
        return date.toString()
    }

    fun dateAsLong():Long {
        val sdf = SimpleDateFormat("mm hh:mm:ss")
        val currentDate = sdf.format(Date())
        val dateAsLong = currentDate.filter {it in '0'..'9'}
        return dateAsLong.toLong()
    }

    fun dateAsInt():Int {
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
        val dateAsInt = currentDate.filter {it in '0'..'9'}
        return dateAsInt.toInt()
    }

    fun date():String {
        return LocalDateTime.now().toString()
    }

}