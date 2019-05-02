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
    private val interval = Observable.interval(0L, 10L, TimeUnit.MILLISECONDS)
    private val interval2 = Observable.interval(0L, 25L, TimeUnit.MILLISECONDS)

    fun test() {
        zipTest()
        zipTest2()
        zipInterval()
        zipWith()

        combineLatest()

        merge()
        concat()
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
            interval,
            BiFunction { source: String, _: Long -> source }
        ).subscribe(UnitObserver(subject))
        Thread.sleep(100L)
    }

    fun zipWith() {
        val subject = "zipWith"
        Util.showStartTime(subject)
        Observable.zip(
            numbers,
            numbers2,
            BiFunction { num1: Int, num2: Int -> num1 + num2 })
            .zipWith(numbers3, BiFunction { sum: Int, num3: Int -> sum + num3 })
            .zipWith(interval, BiFunction { sum: Int, _: Long -> sum })
            .subscribe(UnitObserver(subject))
        Thread.sleep(100L)
    }

    fun combineLatest() {
        var subject = "combineLatest 비교대상"
        Util.showStartTime(subject)
        Observable.zip(
            sources.zipWith(interval, BiFunction { source: String, _: Long -> source }),
            numbers.zipWith(interval2, BiFunction { num: Int, _: Long -> num }),
            BiFunction<String, Int, String> { source: String, number: Int -> source + number }
        ).subscribe(UnitObserver(subject))

        Thread.sleep(200L)

        subject = "combineLatest"
        Util.showStartTime(subject)
        Observable.combineLatest(
            sources.zipWith(interval, BiFunction { source: String, _: Long -> source }),
            numbers.zipWith(interval2, BiFunction { num: Int, _: Long -> num }),
            BiFunction<String, Int, String> { source: String, number: Int -> source + number }
        ).subscribe(UnitObserver(subject))

        Thread.sleep(200L)
    }

    fun merge() {
        val subject = "merge"
        Util.showStartTime(subject)
        Observable.merge(
            sources.zipWith(interval, BiFunction { source: String, _: Long -> source }),
            numbers.zipWith(interval2, BiFunction { num: Int, _: Long -> num })
        ).subscribe(UnitObserver(subject))

        Thread.sleep(200L)
    }

    fun concat() {
        val subject = "concat"
        Util.showStartTime(subject)
        Observable.concat(
            sources.zipWith(interval, BiFunction { source: String, _: Long -> source }),
            numbers.zipWith(interval2, BiFunction { num: Int, _: Long -> num })
        ).subscribe(UnitObserver(subject))

        Thread.sleep(200L)
    }
}