package com.exmp.rx

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exmp.rx.databinding.ListBinding

class MainActivity : AppCompatActivity() {

    lateinit var bb: ListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.list)

    }

}
