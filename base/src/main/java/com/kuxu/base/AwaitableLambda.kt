package com.kuxu.base

import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class AwaitableLambda<T>(
    count: Int = 1,
    val Func: (T) -> Unit = {}
) : (T) -> Unit {

    private val latch = CountDownLatch(count)

    private val _callStack = Stack<T>()
    val callStack: List<T> = _callStack

    fun isComplete(count: Long = 1, unit: TimeUnit = TimeUnit.SECONDS): Boolean {
        if (!latch.await(count, unit)) {
            throw TimeoutException()
        }
        return true
    }

    override fun invoke(p1: T) {
        Func(p1)
        latch.countDown()
        _callStack.push(p1)
    }
}