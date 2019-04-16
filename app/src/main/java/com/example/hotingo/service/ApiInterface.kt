package com.example.hotingo.service

import com.example.hotingo.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("signup")
    fun getUserData(@Body user: UserModel) : Call<UserResponce>

    @POST("login")
    fun getLoginData(@Body user: UserModel) : Call<UserResponce>

    @GET("service")
    fun getService (@Header("Authorization") token : String) :Call<ArrayList<UserServiceRespone>>

    @GET("room")
    fun getRoomsService (@Header("Authorization") token :String) :Call<ArrayList<RoomService>>

    @GET("room/{id}")
    fun getRoomOrder(@Header("Authorization")token: String,
                     @Path("id")id:String) :Call<RoomService>

    @POST("room/{roomId}/customer-order")
    fun getReservationRoom (@Body oneRoom : OrderOneRoom ,@Header("Authorization") token: String,
                            @Path("roomId") roomId :String) :Call<ReservationRoom>

    @GET("room/{user}/customer-order")
    fun getRoomForOneUser(@Header("Authorization") token: String ,
                          @Path("user") user:String) :Call<ArrayList<RoomsResponse>>




}