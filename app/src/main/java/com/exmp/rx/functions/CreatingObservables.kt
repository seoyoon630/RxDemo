package com.exmp.rx.functions

import com.exmp.rx.Animal
import com.exmp.rx.Util
import com.exmp.rx.observer.UnitObserver
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 * 생성 연산자
 */
class CreatingObservables {
    private val arrayData = arrayOf(1, 2, 3, 4, 5)
    private val data = ArrayList<Int>()

    fun test() {
        data.addAll(arrayData)
        intervalTest()
        intervalRangeTest()
        timerTest()
        createTest()
        justTest()
        fromCallableTest()
        fromArrayTest()
        fromIterableTest()
        rangeTest()
        repeatTest()
    }

    fun intervalTest() {
        val subject = "interval"
        Util.showStartTime(subject)
        Observable.interval(0, 50, TimeUnit.MILLISECONDS)
            .map { data -> ((data + 1) * 100).toInt() }
            .take(5)
            .subscribe(UnitObserver(subject))
    }

    fun timerTest() {
        val subject = "timer"
        Util.showStartTime(subject)
        Observable.timer(100, TimeUnit.MILLISECONDS)
            .map { data -> ((data + 1) * 100).toInt() }
            .subscribe(UnitObserver(subject))
    }

    fun createTest() {
        val subject = "create"
        Util.showStartTime(subject)
        Observable.create(ObservableOnSubscribe<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3)
            emitter.onNext(4)
            emitter.onNext(5)
            // emitter.onError(Throwable())
            emitter.onComplete()
        })
            .subscribe(UnitObserver(subject))
    }

    fun justTest() {
        val subject = "just"
        Util.showStartTime(subject)
        Observable.just(1, 2, 3, 4, 5)
            .subscribe(UnitObserver(subject))
    }

    fun fromArrayTest() {
        val subject = "fromArray"
        Util.showStartTime(subject)
        Observable.fromArray(arrayOf(1, 2, 3, 4, 5))
            .subscribe(UnitObserver(subject))
    }

    fun fromIterableTest() {
        val subject = "fromIterable"
        Util.showStartTime(subject)
        val data = ArrayList<Int>()
        data.addAll(arrayOf(1, 2, 3, 4, 5))
        Observable.fromIterable(data)
            .subscribe(UnitObserver(subject))
    }

    fun fromCallableTest() {
        val subject = "fromCallable"
        Util.showStartTime(subject)
        Observable.fromCallable(object : Callable<ArrayList<Int>> {
            override fun call(): ArrayList<Int> {
                Thread.sleep(200)
                return data
            }
        })
            .subscribe(UnitObserver(subject))
    }

    fun rangeTest() {
        val subject = "range"
        Util.showStartTime(subject)
        Observable.range(1, 5)
            .subscribe(UnitObserver(subject))
    }

    fun intervalRangeTest() {
        val subject = "intervalRange"
        Util.showStartTime(subject)
        Observable.intervalRange(1, 5, 0, 10, TimeUnit.MILLISECONDS)
            .map { data -> (data * 100).toInt() }
            .subscribe(UnitObserver(subject))
    }

    fun deferTest() {
        val subject = "defer"
        Util.showStartTime(subject)

        val animal = Animal()

        val deferObserver = Observable.defer { Observable.just(animal.name) }
        val justObserver = Observable.just(animal.name)

        animal.name = "lion"

        justObserver.subscribe(UnitObserver("$subject 비교대상"))
        deferObserver.subscribe(UnitObserver(subject))
    }

    fun repeatTest() {
        val subject = "repeat"
        Util.showStartTime(subject)

        Observable.fromArray(arrayOf(1,2,3))
            .repeat(2)  // empty parameter : infinitely repeat
            .subscribe(UnitObserver(subject))
    }
}