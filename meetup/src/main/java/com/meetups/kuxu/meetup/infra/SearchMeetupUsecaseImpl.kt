package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.domain.SearchMeetupUsecase
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

internal class SearchMeetupUsecaseImpl(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService
) : SearchMeetupUsecase {
  override fun execute(): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      val meetupList = meetupRepository.loadMeetupList().await()
      meetupList?.let {
        it.map {
          val distance = currentLocationService.distanceKmToCurrentLocation(it.meetupLocation).await()
          it.copy(distance = distance)
        }
          .filter {
            it.distance < 100
          }
      }
    }
  }
}