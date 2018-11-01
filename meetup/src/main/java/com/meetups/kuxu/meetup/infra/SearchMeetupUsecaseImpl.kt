package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.SearchMeetupUseacase
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import com.meetups.kuxu.meetup.entity.SearchQueryEntity
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consume
import kotlinx.coroutines.experimental.channels.produce

internal class SearchMeetupUsecaseImpl(
  private val meetupListDataStore: MeetupListDataStore,
  private val currentLocationService: CurrentLocationService
) : SearchMeetupUseacase {
  override fun execute(query: SearchQueryEntity): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    var snapShot =
      meetupListDataStore
        .searchMeetupList(query.keyword)
        .receive()
        .events
        .map {
          MeetupEntityConverter.convert(it)
        }
        .map {
          currentLocationService
            .distanceKmToCurrentLocation(it.meetupLocation)
            .consume {
              val distance = this.receive()
              it.copy(distance = distance)
            }
        }
    if (query.NearMeetup) {
      snapShot = snapShot.filter { it.distance < 100 }
    }

    send(snapShot)
  }
}