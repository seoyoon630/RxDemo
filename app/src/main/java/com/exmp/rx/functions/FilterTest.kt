package com.exmp.rx.functions

import com.exmp.rx.observer.UnitObserver
import com.exmp.rx.observer.UnitSingleObserver
import io.reactivex.Observable

class FilterTest {

    fun test() {
        val observable = Observable.just(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        )
        System.out.println("filter====================================================")
        observable
            .filter { it > 5 }
            .subscribe(UnitObserver("filter"))

        System.out.println("first====================================================")
        observable
            .first(-1)
            .subscribe(UnitSingleObserver("first"))

        System.out.println("last====================================================")
        observable
            .last(99)
            .subscribe(UnitSingleObserver("last"))

        System.out.println("take====================================================")
        observable
            .take(4)
            .subscribe(UnitObserver("take"))

        System.out.println("takeLast====================================================")
        observable
            .takeLast(4)
            .subscribe(UnitObserver("takeLast"))

        System.out.println("skip====================================================")
        observable
            .skip(4)
            .subscribe(UnitObserver("skip"))

        System.out.println("skipLast====================================================")
        observable
            .skipLast(4)
            .subscribe(UnitObserver("skipLast"))
    }
}
