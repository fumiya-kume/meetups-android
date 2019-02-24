package com.kuxu.overview.infra.usecase

import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.MeetupRepository
import com.kuxu.overview.entity.MeetupEntity
import com.kuxu.overview.entity.Prefecture
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.util.*

class LoadMeetupOverviewListUsecaseImplTest {
    @Test
    fun オーバービュー設定が構成されていない時は読み込まない() = runBlocking {
        val choosePrefectureRepository = mockkClass(ChoosePrefectureRepository::class)
        coEvery { choosePrefectureRepository.hasConfigured() } returns false

        val meetupRepository = mockkClass(MeetupRepository::class)

        val loadMeetupOverviewListUsecase = LoadMeetupOverviewListUsecaseImpl(
            choosePrefectureRepository,
            meetupRepository
        )

        loadMeetupOverviewListUsecase.execute()

        verify(exactly = 1) {
            runBlocking {
                choosePrefectureRepository.hasConfigured()
            }
        }

        verify(exactly = 0) {
            runBlocking {
                choosePrefectureRepository.loadChoosePrefecture()
            }
        }

        verify(exactly = 0) {
            runBlocking {
                meetupRepository.searchMeetupByPrefectureList(emptyList())
            }
        }

        Assert.assertEquals(3, 1 + 2)
    }

    @Test
    fun オーバービュー設定が構成されている場合は勉強会リストを条件を利用して検索する() = runBlocking {
        val choosePrefectureRepository = mockkClass(ChoosePrefectureRepository::class)
        coEvery { choosePrefectureRepository.hasConfigured() } returns true

        val prefectureList = listOf(
            Prefecture(0, "愛知"),
            Prefecture(1, "北海道")
        )
        coEvery { choosePrefectureRepository.loadChoosePrefecture() } returns prefectureList

        val meetupRepository = mockkClass(MeetupRepository::class)

        val meetupList = listOf<MeetupEntity>(
            MeetupEntity(0, "タイトル", Date(2018, 10, 10), "ヒカリエ", 10, 20),
            MeetupEntity(1, "タイトル", Date(2018, 10, 10), "ヒカリエ", 10, 20)
        )

        coEvery { meetupRepository.searchMeetupByPrefectureList(prefectureList) } returns meetupList

        val loadMeetupOverviewListUsecase = LoadMeetupOverviewListUsecaseImpl(
            choosePrefectureRepository,
            meetupRepository
        )

        val executeResult = loadMeetupOverviewListUsecase.execute()

        verify(exactly = 1) {
            runBlocking {
                choosePrefectureRepository.hasConfigured()
            }
        }

        verify(exactly = 1) {
            runBlocking {
                choosePrefectureRepository.loadChoosePrefecture()
            }
        }

        verify(exactly = 1) {
            runBlocking {
                meetupRepository.searchMeetupByPrefectureList(prefectureList)
            }
        }

        Assert.assertArrayEquals(meetupList.toTypedArray(), executeResult.toTypedArray())
    }
}