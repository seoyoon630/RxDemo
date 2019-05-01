package com.exmp.rx.functions

import com.exmp.rx.observer.UnitObserver
import com.exmp.rx.observer.UnitSingleObserver
import io.reactivex.Observable

class FilteringObservables {
    private val observable = Observable.just(1, 2, 3, 4, 5)

    fun test() {
        filterTest()
        firstTest()
        lastTest()
        takeTest()
        takeLastTest()
        skipTest()
        skipLastTest()
    }

    fun filterTest() {
        observable
            .filter { it > 2 }
            .subscribe(UnitObserver("filter"))
    }

    fun firstTest() {
        observable
            .first(-1)
            .subscribe(UnitSingleObserver("first"))
    }

    fun lastTest() {
        observable
            .last(99)
            .subscribe(UnitSingleObserver("last"))
    }

    fun takeTest() {
        observable
            .take(4)
            .subscribe(UnitObserver("take"))
    }

    fun takeLastTest() {
        observable
            .takeLast(4)
            .subscribe(UnitObserver("takeLast"))
    }

    fun skipTest() {
        observable
            .skip(4)
            .subscribe(UnitObserver("skip"))

    }

    fun skipLastTest() {
        observable
            .skipLast(4)
            .subscribe(UnitObserver("skipLastTest"))
    }

}
