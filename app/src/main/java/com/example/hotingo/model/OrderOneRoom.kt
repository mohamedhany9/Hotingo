package com.example.hotingo.model

import com.google.gson.annotations.SerializedName

data class OrderOneRoom(@SerializedName("phone") val phone :String
                        ,@SerializedName("duration") val duration :String)
