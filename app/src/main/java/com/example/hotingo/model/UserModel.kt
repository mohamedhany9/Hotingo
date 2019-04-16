package com.example.hotingo.model

import com.google.gson.annotations.SerializedName

data class UserModel(@SerializedName("name") val name: String? = null,
                     @SerializedName("phone") val phone: String,
                     @SerializedName("email") val email: String?=null,
                     @SerializedName("password") val password: String
                )
