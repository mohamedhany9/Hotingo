package com.example.hotingo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotingo.model.UserModel
import com.example.hotingo.model.UserResponce
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var gson: Gson = Gson()
    lateinit var shared: SharedPreferences
    lateinit var shareEditor: SharedPreferences.Editor
    var json: String = ""
    var retriveuser:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerTextView.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        userButtonLogin.setOnClickListener {


            var userphone = userPhone.text.toString()
            var userpassword = userPassword.text.toString()

            if (userphone.isEmpty()) {
                userPhone.setError("Enter Phone Number")
            } else if (userpassword.isEmpty()) {
                userPassword.setError("Enter Password")
            } else {
                var apiInterface: ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)
                val call: Call<UserResponce> =
                    apiInterface.getLoginData(UserModel(phone = userphone, password = userpassword))

                call.enqueue(object : Callback<UserResponce> {
                    override fun onFailure(call: Call<UserResponce>?, t: Throwable?) {

                        Toast.makeText(this@LoginActivity, "Fail", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<UserResponce>?, response: Response<UserResponce>?) {
                   //     Toast.makeText(this@LoginActivity, "LoginActivity Success", Toast.LENGTH_LONG).show()

                       var usermodel : UserResponce? = response?.body()
                        shared = getSharedPreferences("Hotingo",Context.MODE_PRIVATE)
                        shareEditor =shared.edit()
                        json = gson.toJson(usermodel)
                        shareEditor.putString("USER",json)
                        shareEditor.commit()

//                        Toast.makeText(this@LoginActivity,""+shared.getString("USER","bg"),Toast.LENGTH_LONG).show()
//

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })

            }

        }
    }
}