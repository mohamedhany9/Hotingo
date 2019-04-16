package com.example.hotingo.model

import com.google.gson.annotations.SerializedName

data class ReservationRoom(
    @SerializedName("__v")
    val v: Int?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("creationDate")
    val creationDate: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("room")
    val room: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user")
    val user: String?
)