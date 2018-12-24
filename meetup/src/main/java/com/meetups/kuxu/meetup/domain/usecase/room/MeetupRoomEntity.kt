package com.meetups.kuxu.meetup.domain.usecase.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "meetup")
class MeetupRoomEntity (
  @PrimaryKey
  @NotNull
  val id: Int = 0,
  val title: String = "",
  @ColumnInfo(name = "meetup_link")
  val meetupLink: String = "",
  val lat: Double = 0.0,
  val lon: Double = 0.0
)