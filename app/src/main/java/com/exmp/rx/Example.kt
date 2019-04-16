package com.exmp.rx

import com.exmp.rx.observer.UnitMaybeObserver
import com.exmp.rx.observer.UnitObserver
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class Example {
    val gugudan = "gugudan"
    val sales = "sales"

    fun createGugudan(input: Int) {
        for (i in 1..9) {
            System.out.println("$input * $i = ${input * i}")
        }
    }

    fun createGugudan2(input: Int) {
        val observable = Observable.range(1, 9)
            .map { "$input * $it = ${input * it}" }
        observable.subscribe(UnitObserver(gugudan))
    }

    fun createGugudan3() {
        val observable = Observable.range(1, 9)
        observable
            .flatMap { num1 ->
                Observable.range(1, 9)
                    .map { num2 -> "$num1 * $num2 = ${num1 * num2}" }
            }
            .subscribe(UnitObserver(gugudan))
    }

    fun sales() {
        val data = ArrayList<Pair<String, Int>>()
        data.add(Pair("TV", 2500))
        data.add(Pair("Camera", 300))
        data.add(Pair("TV", 1600))
        data.add(Pair("Phone", 800))

        Observable.fromIterable(data)
            .filter { it.first == "TV" }
            .map { it.second }
            .reduce { d1, d2 -> d1 + d2 }
            .subscribe(UnitMaybeObserver(sales))
    }
}