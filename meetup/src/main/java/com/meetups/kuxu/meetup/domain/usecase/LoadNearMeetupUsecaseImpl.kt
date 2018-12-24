package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupDao
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupRoomEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

internal class LoadNearMeetupUsecaseImpl(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService,
  private val meetupDao: MeetupDao
) : LoadNearMeetupUsecase {
  override fun execute(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    runBlocking {

      send(
        meetupDao.readAll()
          .map { MeetupEntityConverter.convert(it) }
          .map { it.copy(distance = currentLocationService.distanceTo(it.meetupLocation).await()) }
      )

      meetupRepository.loadMeetupList()
        .await()
        ?.let {
          meetupDao.deleteAll()
          meetupDao.insertAll(
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
          )


          val resultList =
            it.map { it.copy(distance = currentLocationService.distanceTo(it.meetupLocation).await()) }
              .filter { it.distance < 100 }
          send(resultList)
        }
    }
  }
}