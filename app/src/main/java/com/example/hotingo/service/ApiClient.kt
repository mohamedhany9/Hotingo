package com.example.hotingo.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {


    companion object {

        val Base_URL: String = ""

        var retrofit: Retrofit? = null


        fun getUser(): Retrofit {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit!!

            } else return retrofit!!
        }

    }
}
