package com.kuxu.base

import org.junit.Assert
import org.junit.Test
import java.util.concurrent.TimeoutException

internal class awaitableLambdaTest {
    @Test
    fun 関数が一度呼び出される検証を行う() {
        val awaitableLambda = AwaitableLambda<String>()

        awaitableLambda("")

        assert(awaitableLambda.isComplete())
    }

    @Test(expected = TimeoutException::class)
    fun 関数が一度も呼び出されないテスト() {
        val awaitableLambda = AwaitableLambda<String>()
        awaitableLambda.isComplete()
    }

    @Test
    fun 関数が過剰に呼び出されるテスト() {
        val awaitableLambda = AwaitableLambda<String>()
        awaitableLambda("")
        awaitableLambda("")
    }

    @Test
    fun 初期状態ではコールスタックに何も積まれていないテスト() {
        val awaitableLambda = AwaitableLambda<String>()
        Assert.assertEquals(0, awaitableLambda.callStack.count())
    }

    @Test
    fun コールスタックに１つ積まれるテスト() {
        val awaitableLambda = AwaitableLambda<String>()
        awaitableLambda("Hello")
        Assert.assertEquals("Hello", awaitableLambda.callStack.elementAt(0))
    }

    @Test
    fun コールスタックに複数個積まれるテスト() {
        val awaitableLambda = AwaitableLambda<String>()
        awaitableLambda("Hello")
        awaitableLambda("Work")
        Assert.assertEquals("Hello", awaitableLambda.callStack.elementAt(0))
        Assert.assertEquals("Work", awaitableLambda.callStack.elementAt(1))
    }
}