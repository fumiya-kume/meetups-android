package com.kuxu.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

@RunWith(Theories::class)
class AwaitableObserberTest {

    companion object {
        @DataPoints
        @JvmField
        val dataList = listOf(
            listOf(1, 2),
            listOf(1, 2, 3),
            listOf(1, 2, 3, 4),
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6)
        )
    }

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun 正常に１回呼ばれて終了する() {
        val liveData = MutableLiveData<String>()

        val awaitableObserber = AwaitableObserber<String>()

        liveData.observeForever(awaitableObserber)

        liveData.postValue("Hello")

        awaitableObserber.await()

        val lastValue = awaitableObserber.getLastValue()

        Assert.assertEquals("Hello", lastValue)
    }

    @Test(expected = TimeoutException::class)
    fun 呼ばれない場合は失敗する() {
        val liveData = MutableLiveData<String>()

        val awaitableObserber = AwaitableObserber<String>()

        liveData.observeForever(awaitableObserber)

        awaitableObserber.await()

        val lastValue = awaitableObserber.getLastValue()

        Assert.assertEquals("Hello", lastValue)
    }

    @Theory
    fun 正常に複数回呼ばれて終了する(data: List<Int>) {
        val liveData = MutableLiveData<Int>()

        val awaitableObserber = AwaitableObserber<Int>(data.count())

        liveData.observeForever(awaitableObserber)

        data.forEach { liveData.postValue(it) }

        awaitableObserber.await()

        val lastValue = awaitableObserber.getLastValue()

        Assert.assertEquals(data.last(), lastValue)
        Assert.assertEquals(data, awaitableObserber.valueLog)
    }

    @Theory
    @Test(expected = TimeoutException::class)
    fun 複数回の場合でも規定回数呼ばれない場合は失敗する(data: List<Int>) {
        val liveData = MutableLiveData<Int>()

        val awaitableObserber = AwaitableObserber<Int>(data.count())

        liveData.observeForever(awaitableObserber)

        val newData = data.toMutableList().apply { remove(0) }

        newData.forEach { liveData.postValue(it) }

        awaitableObserber.await()
        val hoge: MediatorLiveData<String>
    }
}