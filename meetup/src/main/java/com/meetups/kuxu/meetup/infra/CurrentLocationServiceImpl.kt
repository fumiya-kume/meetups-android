package com.meetups.kuxu.meetup.infra

import android.content.Context
import androidx.core.content.PermissionChecker
import com.google.android.gms.location.FusedLocationProviderClient
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.entity.CurrentLocationEntity
import io.reactivex.Maybe

internal class CurrentLocationServiceImpl(
  private val context: Context
) : CurrentLocationService {
  override fun loadCurrentLocation(): Maybe<CurrentLocationEntity> = Maybe.create { emitter ->
    if (PermissionChecker.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
      ) != PermissionChecker.PERMISSION_GRANTED
    ) {
      emitter.onComplete()
    } else {

      val locationProviderClient = FusedLocationProviderClient(context)
      val lastLocation = locationProviderClient.lastLocation
      lastLocation.addOnSuccessListener {
        emitter.onSuccess(CurrentLocationEntity(it.latitude, it.longitude))
      }.addOnFailureListener {
        emitter.onError(it)
      }
    }
  }
}