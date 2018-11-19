package com.meetups.kuxu.meetup.service

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

internal class ConnpassEventPageViewerServiceImpl(
  private val context: Context
) : ConnpassEventPageViewerService {
  override fun showEventPage(eventPageUrl: String) {
    val intent = CustomTabsIntent
      .Builder()
      .build()
    intent.launchUrl(context, Uri.parse(eventPageUrl))
  }
}
