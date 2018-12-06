package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupDatabase
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupRoomEntity
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

internal class LoadNearMeetupUsecaseImpl(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService,
  private val meetupDatabase: MeetupDatabase
) : LoadNearMeetupUsecase {
  override fun execute(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    runBlocking {

      val readResult =
        meetupDatabase.meetupDao().readAll()
          .map { MeetupEntityConverter.convert(it) }
          .map { it.copy(distance = currentLocationService.distanceTo(it.meetupLocation).await()) }
      send(readResult)

      meetupRepository.loadMeetupList()
        .await()
        ?.let {
          meetupDatabase.meetupDao().deleteAll()
          it
            .map {
              MeetupRoomEntity(
                id = it.id,
                title = it.title,
                meetupLink = it.meetupLink,
                lat = it.meetupLocation.lat,
                lon = it.meetupLocation.lon
                )
            }
            .map { meetupDatabase.meetupDao().insert(it) }

          val resultList = it.map { it.copy(distance = currentLocationService.distanceTo(it.meetupLocation).await()) }
            .filter { it.distance < 100 }
          send(resultList)
        }
    }
  }
}