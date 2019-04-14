package com.kuxu.api

import com.google.firebase.firestore.FirebaseFirestore
import com.kuxu.api.entity.Event
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Suppress("CAST_NEVER_SUCCEEDS")
class ConnpassClientImpl : ConnpassClient {
    private val db = FirebaseFirestore.getInstance()

    override suspend fun loadAllEventList(): List<Event> = suspendCoroutine { cond ->
        db
            .collection("event")
            .limit(100)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    throw it
                }
                if (querySnapshot == null) cond.resume(emptyList())
                cond.resume(querySnapshot?.toObjects(Event::class.java) ?: emptyList())
            }
    }
}
