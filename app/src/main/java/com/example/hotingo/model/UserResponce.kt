package com.example.hotingo.model

import com.google.gson.annotations.SerializedName

data class UserResponce(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: UserX?
)