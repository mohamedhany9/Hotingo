package com.example.hotingo.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hotingo.HomeActivity
import com.example.hotingo.LoginActivity
import com.example.hotingo.R
import com.example.hotingo.RoomServiceFragment
import com.example.hotingo.model.UserResponce
import com.google.gson.Gson
import java.util.*

class SplashScreen : AppCompatActivity() {
    var gson: Gson = Gson()
    lateinit var shared: SharedPreferences
    var retriveuser: String = ""
    lateinit var shareEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Timer().schedule(object : TimerTask() {
            override fun run() {
                shared = getSharedPreferences("Hotingo", Context.MODE_PRIVATE)
                shareEditor = shared.edit()

                retriveuser = shared.getString("USER", "")
                var user = gson.fromJson(retriveuser, UserResponce::class.java) ?: null

                if (user == null) {
                    val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@SplashScreen, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 1200)
    }
}
