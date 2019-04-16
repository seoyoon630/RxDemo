package com.exmp.rx.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class UnitSingleObserver(var subject: String = "") : SingleObserver<Any> {

    override fun onSubscribe(d: Disposable) {
        System.out.println("$subject onSubscribe!")
    }

    override fun onSuccess(data: Any) {
        if (data is ArrayList<*>) {
            val it = data.iterator()
            while (it.hasNext()) {
                val d = it.next()
                System.out.println("$subject onNext : $d")
            }
        } else {
            System.out.println("$subject onNext : $data")
        }
    }

    override fun onError(e: Throwable) {
        System.out.println("$subject onError : ${e.message}\n${e.stackTrace}")
    }

}