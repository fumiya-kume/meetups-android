package com.meetups.kuxu.meetup.entity

internal data class MeetupEntity(
  val id: Int,
  val title: String,
  val meetupLink: String,
  val distance: Int,
  val meetupLocation: LocationEntity
)