package com.meetups.kuxu.meetups

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val firebaseMessaging = FirebaseMessaging.getInstance()
    firebaseMessaging.subscribeToTopic("all")
  }
}
