package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

internal class LoadNearMeetupUsecaseImpl(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService
) : LoadNearMeetupUsecase {
  override fun execute(): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      meetupRepository.loadMeetupList()
        .await()
        ?.let {
          it.map { it.copy(distance = currentLocationService.distanceTo(it.meetupLocation).await()) }
            .filter { it.distance < 100 }
        }
    }
  }
}