package com.exmp.rx.observer

import io.reactivex.MaybeObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class UnitMaybeObserver(var subject: String = "") : MaybeObserver<Any> {
    override fun onSubscribe(d: Disposable) {
    }

    override fun onSuccess(data: Any) {
        when (data) {
            is ArrayList<*> -> {
                val it = data.iterator()
                while (it.hasNext()) {
                    val d = it.next()
                    System.out.println("$subject onSuccess : $d")
                }
            }
            is Array<*> -> for (d in data) {
                System.out.println("$subject onSuccess : $d")
            }
            else -> System.out.println("$subject onSuccess : $data")
        }
    }

    override fun onComplete() {
        System.out.println("$subject onComplete!")
    }

    override fun onError(e: Throwable) {
        System.out.println("$subject onError : ${e.message}\n${e.stackTrace}")
    }

}