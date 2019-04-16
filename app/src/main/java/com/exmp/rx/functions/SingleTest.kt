package com.exmp.rx.functions

import com.exmp.rx.observer.UnitSingleObserver
import io.reactivex.Single

class SingleTest {

    val data = ArrayList<Int>()

    fun test() {
        val subject = "single"
        createDummyData()
        Single.fromCallable { data }
            .subscribe(UnitSingleObserver(subject))
    }

    private fun createDummyData() {
        data.clear()
        data.add(1)
        data.add(2)
        data.add(3)
        data.add(4)
        data.add(5)
        data.add(6)
        data.add(7)
        data.add(8)
        data.add(9)
        data.add(10)

    }
}
