package com.kuxu.settingpage.infra

import com.kuxu.config.PrefectureInfoRepository
import com.kuxu.settingpage.bindingModel.PrefectureBindingModel
import com.kuxu.settingpage.domain.PrefectureRepository
import com.kuxu.usersetting.UserSetting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PrefectureRepositoryImpl(
    private val prefectureInfoRepository: PrefectureInfoRepository,
    private val userSetting: UserSetting
) : PrefectureRepository {
    override suspend fun loadPrefectureList(): List<PrefectureBindingModel> {
        return withContext(Dispatchers.IO) {
            val prefectureList = userSetting.loadChoosedPrefectureList().list
            prefectureInfoRepository.loadPrefectureList()
                .map { prefecture ->
                    PrefectureBindingModel(
                        prefecture.id,
                        prefecture.name,
                        prefectureList.any { prefecture.id == it }
                    )
                }
        }
    }
}