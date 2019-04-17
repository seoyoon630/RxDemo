package com.exmp.rx.observer

import com.exmp.rx.Util
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UnitObserver(var subject: String = "") : Observer<Any?> {

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(data: Any) {
        when (data) {
            is ArrayList<*> -> {
                val it = data.iterator()
                while (it.hasNext()) {
                    val d = it.next()
                    System.out.println("$subject : $d\t(${Util.getStartTime()} )")
                }
            }
            is Array<*> -> for (d in data) {
                System.out.println("$subject : $d\t${Util.getStartTime()} ")
            }
            else -> System.out.println("$subject : $data\t${Util.getStartTime()} ")
        }
    }

    override fun onComplete() {
        System.out.println("$subject onComlete!")
    }

    override fun onError(e: Throwable) {
        System.out.println("$subject onError : ${e.message}\n${e.stackTrace}")
    }
}