package com.exmp.rx.functions

import com.exmp.rx.observer.UnitObserver
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class CombiningObservables {
    fun test() {
        zipTest1()
        zipTest2()
        combineLatestTest()
    }

    fun zipTest1() {
        val subject = "zip1"
        val shapes = arrayOf("BALL", "PENTAGON", "STAR")
        val coloredTriangles = arrayOf("2-T", "6-T", "4-T")

        val function = BiFunction<Array<String>, Array<String>, ArrayList<String>>
        { t1, t2 ->
            val result = ArrayList<String>()
            t1.forEachIndexed { index, s -> result.add(s + t2[index]) }
            return@BiFunction result
        }

        Observable
            .zip(Observable.fromArray(shapes), Observable.fromArray(coloredTriangles), function)
            .subscribe(UnitObserver(subject))
    }

    fun zipTest2() {
        val subject = "zip2"
        Observable.zip(
            Observable.just(1, 2, 3),
            Observable.interval(200, TimeUnit.MILLISECONDS),
            BiFunction<Int, Long, Int> { t1, _ -> t1 }
        )
            .subscribe(UnitObserver(subject))
        Thread.sleep(5*1000)
    }

    fun combineLatestTest(){

    }
}