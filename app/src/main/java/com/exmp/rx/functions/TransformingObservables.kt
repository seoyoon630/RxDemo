package com.exmp.rx.functions

import com.exmp.rx.Util
import com.exmp.rx.observer.UnitMaybeObserver
import com.exmp.rx.observer.UnitObserver
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * 변환 연산자
 */
class TransformingObservables {

    fun test() {
        mapTest()
        flatMapTest()
        reduceTest()
        scanTest()
    }

    fun mapTest() {
        val subject = "map"
        Util.showStartTime(subject)
        Observable.just(1, 2, 3, 4, 5)
            .map { item -> item + 10 }
            .subscribe(UnitObserver(subject))
    }

    fun flatMapTest() {
        val subject = "flatMap"
        Util.showStartTime(subject)
        Observable.just(1, 2, 3, 4, 5)
            .flatMap { Observable.just(it + 10, it + 20, it + 30) }
            .subscribe(UnitObserver(subject))
    }

    fun concatMapTest() {
        var subject = "concatMap 비교대상"
        Util.showStartTime(subject)
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .flatMap {
                Observable.interval(20, TimeUnit.MILLISECONDS)
                    .map { data -> "${data + 1}번" }
                    .take(2)
            }
            .take(8)
            .subscribe(UnitObserver(subject))
        Thread.sleep(3 * 1000)

        subject = "concatMap"
        Util.showStartTime(subject)
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .concatMap {
                Observable.interval(20, TimeUnit.MILLISECONDS)
                    .map { data -> "${data + 1}번" }
                    .take(2)
            }
            .take(8)
            .subscribe(UnitObserver(subject))
        Thread.sleep(5 * 1000)
    }

    fun switchMapTest() {
        var subject = "switchMap 비교대상"
        Util.showStartTime(subject)
        val balls = arrayOf("1", "3", "5")
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .map(Long::toInt)
            .map { idx -> balls[idx] }
            .take(balls.size.toLong())
            .concatMap { ball ->
                Observable.interval(20, TimeUnit.MILLISECONDS)
                    .map { "$ball<>" }
                    .take(2)
            }
            .subscribe(UnitObserver())
        Thread.sleep(10 * 1000)

        subject = "switchMap"
        Util.showStartTime(subject)
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .map(Long::toInt)
            .map { idx -> balls[idx] }
            .take(balls.size.toLong())
            .switchMap { ball ->
                Observable.interval(20, TimeUnit.MILLISECONDS)
                    .map { "$ball<>" }
                    .take(2)
            }
            .subscribe(UnitObserver())
        Thread.sleep(10 * 1000)
    }

    // todo 모르겠어요
    fun groupByTest() {
        val subject = "groupBy"
        Util.showStartTime(subject)
        val objs = arrayOf("6", "4", "2-T", "2", "6-T", "4-T")
        val source = Observable.fromArray(objs).groupBy { data -> data.forEach { "${it[0]}번" } }
        source.subscribe { obj -> obj.subscribe { value -> System.out.println("Group : " + obj.key + "\t Value : " + value) } }
    }

    fun reduceTest() {
        val subject = "reduce"

//        val observable: Maybe<Int> = Observable.just(1)
//            .filter { it > 1 }
        val observable: Maybe<Int> = Observable.just(1,2,3,4,5)
            .reduce { item1, item2 -> item1 * item2 }
        observable.subscribe(UnitMaybeObserver(subject))
    }

    // reduce scan 차이점
    // MaybeObserver X Observer O
    // reduce 함수는 마지막 값이 입력되지 않거나 onComplete가 발생하지 않으면 값을 발행하지 않지만
    // scan은 값이 입력될 때마다 구독자에게 값을 발행
    fun scanTest() {
        val subject = "scan"
        Observable.just(1, 2, 3, 4, 5)
            .scan { item1, item2 -> item1 * item2 }
            .subscribe(UnitObserver(subject))
    }


}