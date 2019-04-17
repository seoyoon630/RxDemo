package com.exmp.rx

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun showStartTime(subject: String = "", prefix : String = "시작시간") {
            System.out.println("$subject $prefix = ${getStartTime()}")
        }

        fun getStartTime() : String{
            val sdf = SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault())
            return sdf.format(System.currentTimeMillis())
        }
    }
}