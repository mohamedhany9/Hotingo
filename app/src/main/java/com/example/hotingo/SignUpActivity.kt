package com.example.hotingo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotingo.model.UserModel
import com.example.hotingo.model.UserResponce
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userButtonRegister.setOnClickListener {

        var username: String = userName.text.toString()
        var useremail: String = userEmail.text.toString()
        var userphone: String = userPhone.text.toString()
        var userpssword: String = userPassword.text.toString()
        var user_confirm_pasword: String = userConfirmPassword.text.toString()

            if (username.isEmpty())
            {
                userName.setError("Enter User Name")
            }


           else if (useremail.isEmpty())
            {
                userEmail.setError("Enter Email")
            }


            else if (userphone.isEmpty())
            {
                userPhone.setError("Enter Phone Number")
            }


            else if (userpssword.isEmpty())
            {
                userPassword.setError("Enter Password")
            }

            else if (user_confirm_pasword!=userpssword)
            {

                userConfirmPassword.setError("Password not Matching")
            }
            else{
            val apiInterface :ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)
            val call :Call<UserResponce> = apiInterface.getUserData(UserModel(username,userphone,useremail,userpssword))

            call.enqueue(object :Callback<UserResponce>{
                override fun onFailure(call: Call<UserResponce>?, t: Throwable?) {
                    Toast.makeText(this@SignUpActivity,"Fail",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<UserResponce>?, response: Response<UserResponce>?) {
                    Toast.makeText(this@SignUpActivity,"Success : " ,Toast.LENGTH_LONG).show()

                }
            })
        }

    }

}}
