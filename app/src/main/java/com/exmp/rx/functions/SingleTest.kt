package com.exmp.rx.functions

import com.exmp.rx.observer.UnitSingleObserver
import io.reactivex.Single

class SingleTest {


    fun test() {
        val subject = "single"
        Single.just(1)
            .subscribe(UnitSingleObserver(subject))

//        Single.just(1,2,3)
//            .subscribe(UnitSingleObserver(subject))
    }
}
