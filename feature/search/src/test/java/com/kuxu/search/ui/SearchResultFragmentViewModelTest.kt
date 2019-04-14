package com.kuxu.search.ui

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.kuxu.base.AwaitableLambda
import com.kuxu.base.AwaitableObserber
import com.kuxu.search.domain.MeetupSearchUsecase
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.EventEntity
import com.kuxu.search.ui.bindingmodel.SearchResultBindingModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnitPlatform::class)
internal class SearchResultFragmentViewModelTest : Spek({

    val lifecycleOwner = mockkClass(LifecycleOwner::class)
    val lifecycleRegistry = LifecycleRegistry(lifecycleOwner)

    beforeEachTest {
        ArchTaskExecutor
            .getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean {
                    return true
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

            })

        every { lifecycleOwner.lifecycle } returns lifecycleRegistry

    }

    afterEachTest {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    Feature("検索結果を表示することができる") {
        val searchKeyword = ""
        val searchQuery = EventSearchQuery(searchKeyword)

        val meetupResultEntityList =
            listOf(
                EventEntity(
                    0,
                    "タイトル",
                    "2019/1/1 12:10".toDate(),
                    "場所の名前",
                    10,
                    20,
                    "https://yahoo.co.jp"
                )
            )

        val meetupSearchUsecase = mockkClass(MeetupSearchUsecase::class)
        coEvery { meetupSearchUsecase.execute(searchQuery) } returns meetupResultEntityList

        val errorCallback = AwaitableLambda<String>()

        val searchResultLiveDataFactory = SearchResultLiveDataFactory(meetupSearchUsecase)

        val subject by memoized {
            SearchResultFragmentViewModel(
                searchResultLiveDataFactory
            )
        }

        subject.searchError = errorCallback


        val dummyList = listOf(
            SearchResultBindingModel(
                0,
                "タイトル",
                "2019/1/1 12:10",
                "場所の名前",
                10,
                20,
                "https://yahoo.co.jp"
            )
        )

        Scenario("検索クエリを渡されるとそれに従って検索を実施する") {
            When("正常な検索クエリを渡された場合") {

                // LiveData を読み取るLive Data の作成
                val observer = AwaitableObserber<List<SearchResultBindingModel>>()
                subject.searchResultLiveData.observe(
                    lifecycleOwner,
                    observer
                )


                lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

                // 実行
                subject.search(searchQuery)


                // アサーションの前処理の実行
                observer.await()

                // アサーション
                Assert.assertEquals(observer.getLastValue(), dummyList)
            }
        }
    }
})

fun String.toDate(format: String = "yyyy/MM/dd HH:mm"): Date = SimpleDateFormat(format, Locale.JAPAN).parse(this)

