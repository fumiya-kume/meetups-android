package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.LoadNearMeetupUsecase
import com.meetups.kuxu.meetup.domain.MeetupRepository
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
          it.map { it.copy(distance = currentLocationService.distanceKmToCurrentLocation(it.meetupLocation).await()) }
            .filter { it.distance < 100 }
        }
    }
  }
}