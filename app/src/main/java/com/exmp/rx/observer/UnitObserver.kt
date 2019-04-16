package com.exmp.rx.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UnitObserver(var subject: String = "") : Observer<Any?> {

    override fun onComplete() {
        System.out.println("$subject onComlete!")
    }

    override fun onSubscribe(d: Disposable) {
        System.out.println("$subject onSubscribe!")
    }

    override fun onNext(data: Any) {
        when (data) {
            is ArrayList<*> -> {
                val it = data.iterator()
                while (it.hasNext()) {
                    val d = it.next()
                    System.out.println("$subject onNext : $d")
                }
            }
            is Array<*> -> for (d in data) {
                System.out.println("$subject onNext : $d")
            }
            else -> System.out.println("$subject onNext : $data")
        }
    }

    override fun onError(e: Throwable) {
        System.out.println("$subject onError : ${e.message}\n${e.stackTrace}")
    }
}