package com.meetups.kuxu.connpass_api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

// List などのリストをデシリアライズするようになやつを使うときに
inline fun <reified T : Any> Request.awaitResultObject(deserializer: ResponseDeserializable<T>): Deferred<Result<T, FuelError>> {
  val request = this
  return GlobalScope.async {
    return@async request.responseObject(deserializer).third
  }
}

inline fun <reified T : Any> Request.body(item: T): Request {
  val request = this
  val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
  val requestAdapter = moshi.adapter(T::class.java)
  val header = hashMapOf("Content-Type" to "application/json")
  return request.header(header).body(requestAdapter.toJson(item))
}