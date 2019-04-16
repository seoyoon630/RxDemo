package com.exmp.rx

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun showStartTime(subject: String = "") {
            val sdf = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
            val time = sdf.format(System.currentTimeMillis())
            System.out.println("$subject 시작시간 = $time")
        }
    }
}