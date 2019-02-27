package com.kuxu.overview.infra

import com.kuxu.config.PrefectureInfoRepository
import com.kuxu.entity.Prefecture
import com.kuxu.usersetting.UserSetting
import com.kuxu.usersetting.entity.PrefectureList
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class ChoosePrefectureRepositoryImplTest {
    @Test
    fun オーバービューのユーザー設定が構成されているか確認することができる() = runBlocking {
        val userSetting = mockkClass(UserSetting::class)
        val prefectureRepository = mockkClass(PrefectureInfoRepository::class)

        every { userSetting.hasUserSetting() } returns true

        every { userSetting.loadChoosedPrefectureList() } returns PrefectureList(listOf(0, 1, 2))

        every { prefectureRepository.loadPrefectureFromId(0) } returns Prefecture(0, "愛知県")
        every { prefectureRepository.loadPrefectureFromId(1) } returns Prefecture(2, "北海道")
        every { prefectureRepository.loadPrefectureFromId(2) } returns Prefecture(3, "東京都")


        val choosePrefectureRepository = ChoosePrefectureRepositoryImpl(
            userSetting,
            prefectureRepository
        )

        val result = choosePrefectureRepository.hasConfigured()

        Assert.assertTrue(result)
    }

    @Test
    fun ユーザーの選択した県の一覧を取得することができる() = runBlocking {
        val userSetting = mockkClass(UserSetting::class)
        val prefectureRepository = mockkClass(PrefectureInfoRepository::class)

        every { userSetting.hasUserSetting() } returns true

        every { userSetting.loadChoosedPrefectureList() } returns PrefectureList(listOf(0, 1, 2))

        every { prefectureRepository.loadPrefectureFromId(0) } returns Prefecture(0, "愛知県")
        every { prefectureRepository.loadPrefectureFromId(1) } returns Prefecture(2, "北海道")
        every { prefectureRepository.loadPrefectureFromId(2) } returns Prefecture(3, "東京都")


        val choosePrefectureRepository = ChoosePrefectureRepositoryImpl(
            userSetting,
            prefectureRepository
        )

        val result = choosePrefectureRepository.loadChoosePrefecture()

        Assert.assertEquals(3, result.count())
        Assert.assertEquals(result.elementAt(0).id, com.kuxu.overview.entity.Prefecture(0, "愛知県").id)
        Assert.assertEquals(result.elementAt(0).name, com.kuxu.overview.entity.Prefecture(0, "愛知県").name)
    }
}