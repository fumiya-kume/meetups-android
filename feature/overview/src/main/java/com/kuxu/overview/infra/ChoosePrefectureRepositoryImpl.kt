package com.kuxu.overview.infra

import com.kuxu.config.PrefectureInfoRepository
import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.entity.convert
import com.kuxu.usersetting.UserSetting

internal class ChoosePrefectureRepositoryImpl(
    private val userSetting: UserSetting,
    private val prefectureInfoRepository: PrefectureInfoRepository
) : ChoosePrefectureRepository {
    override suspend fun hasConfigured(): Boolean {
        return userSetting.hasUserSetting() && loadChoosePrefecture().isNotEmpty()
    }

    override suspend fun loadChoosePrefecture() =
        userSetting.loadChoosedPrefectureList()
            .list
            .map { prefectureInfoRepository.loadPrefectureFromId(it) }
            .convert()
}