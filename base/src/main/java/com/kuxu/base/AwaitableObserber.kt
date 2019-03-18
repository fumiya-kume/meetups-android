package com.kuxu.base

import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class AwaitableObserber<T>(count: Int = 1) : Observer<T> {

    private val latch = CountDownLatch(count)

    private val _valueLog = mutableListOf<T>()

    val valueLog: List<T> = _valueLog

    fun getLastValue(): T? = valueLog.lastOrNull()

    fun await(count: Long = 1, unit: TimeUnit = TimeUnit.SECONDS) {
        if (!latch.await(count, unit)) {
            throw TimeoutException()
        }
    }

    override fun onChanged(t: T) {
        _valueLog.add(t)
        latch.countDown()
    }
}