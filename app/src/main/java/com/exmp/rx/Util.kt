package com.exmp.rx

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun showStartTime(subject: String = "") {
            System.out.println("$subject 시작시간 = ${getTime()}")
        }

        fun getTime(): String {
            val sdf = SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault())
            return sdf.format(System.currentTimeMillis())
        }
    }
}