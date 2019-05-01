package com.exmp.rx.functions

import com.exmp.rx.Util
import com.exmp.rx.observer.UnitObserver
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.util.concurrent.TimeUnit

class CombiningObservables {
    private val sources = Observable.just("A", "B", "C")
    private val numbers = Observable.just(1, 2, 3, 4)
    private val numbers2 = Observable.just(10, 20, 30, 40)
    private val numbers3 = Observable.just(100, 200, 300, 400)

    fun test() {
        zipTest()
        zipTest2()
        zipInterval()
        zipWith()
    }

    fun zipTest() {
        val subject = "zip"
        Util.showStartTime(subject)
        Observable.zip(
            sources,
            numbers,
            BiFunction<String, Int, String> { source: String, number: Int -> source + number }
        ).subscribe(UnitObserver(subject))
    }

    fun zipTest2() {
        val subject = "zip2"
        Util.showStartTime(subject)
        Observable.zip(
            numbers,
            numbers2,
            numbers3,
            Function3<Int, Int, Int, Int> { num1, num2, num3 -> num1 + num2 + num3 }
        ).subscribe(UnitObserver(subject))
    }

    fun zipInterval() {
        val subject = "zipInterval"
        Util.showStartTime(subject)
        Observable.zip(
            sources,
            Observable.interval(0L, 10L, TimeUnit.MILLISECONDS),
            BiFunction { source: String, _: Long -> source }
        ).subscribe(UnitObserver(subject))
        Thread.sleep(100L)
    }

    fun zipWith(){
        val subject = "zipWith"
        Util.showStartTime(subject)
        Observable.zip(
            sources,
            Observable.interval(0L, 10L, TimeUnit.MILLISECONDS),
            BiFunction { source: String, _: Long -> source }
        ).subscribe(UnitObserver(subject))
        Thread.sleep(100L)
    }

}