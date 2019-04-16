package com.exmp.rx.observer

import io.reactivex.MaybeObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class UnitMaybeObserver(var subject: String = "") : MaybeObserver<Any> {
    override fun onSubscribe(d: Disposable) {
        System.out.println("$subject onSubscribe!")
    }

    override fun onSuccess(data: Any) {
        System.out.println("$subject onNext : $data")
    }

    override fun onComplete() {
        System.out.println("$subject onComplete!")
    }

    override fun onError(e: Throwable) {
        System.out.println("$subject onError : ${e.message}\n${e.stackTrace}")
    }

}