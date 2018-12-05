package com.meetups.kuxu.meetups

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

  override fun onMessageReceived(p0: RemoteMessage?) {
    // メッセージを受け取った時にあれこれする
  }

  override fun onNewToken(p0: String?) {
    // 新しいTokenが配布された時に呼び出される
  }
}
