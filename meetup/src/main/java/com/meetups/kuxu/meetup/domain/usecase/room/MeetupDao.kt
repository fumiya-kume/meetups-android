package com.meetups.kuxu.meetup.domain.usecase.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MeetupDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(roomEntityList: List<MeetupRoomEntity>)

  @Query("select * from meetup")
  fun readAll(): List<MeetupRoomEntity>

  @Query("delete from meetup")
  fun deleteAll()
}