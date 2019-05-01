package com.exmp.rx

import com.exmp.rx.functions.*
import org.junit.Test

/**
 * Example local unit realTest, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RxUnitTest {

    @Test
    fun singleTest() {
        val test = SingleTest()
        test.test()
    }

    @Test
    fun gugudan() {
        val gugudan = Example()
        gugudan.createGugudan()
    }

    @Test
    fun sales() {
        val test = Example()
        test.sales()
    }

    @Test
    fun creatingObservables() {
        val test = CreatingObservables()
        test.test()
    }

    @Test
    fun deferTest() {
        val test = CreatingObservables()
        test.deferTest()
    }

    @Test
    fun transformingObservables() {
        val test = TransformingObservables()
        test.test()
    }

    @Test
    fun reduceTest() {
        val test = TransformingObservables()
        test.reduceTest()
    }

    @Test
    fun concatMapTest() {
        val test = TransformingObservables()
        test.concatMapTest()
    }

    @Test
    fun switchMapTest() {
        val test = TransformingObservables()
        test.switchMapTest()
    }

    @Test
    fun groupByTest() {
        val test = TransformingObservables()
        test.groupByTest()
    }

    @Test
    fun filteringObservables() {
        val test = FilteringObservables()
        test.test()
    }

    @Test
    fun combiningObservables() {
        val test = CombiningObservables()
        test.test()
    }
}
