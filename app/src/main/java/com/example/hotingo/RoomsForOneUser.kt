package com.example.hotingo


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotingo.Adapter.RoomResponseAdapter
import com.example.hotingo.model.RoomsResponse
import com.example.hotingo.model.UserResponce
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_rooms_for_one_user.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RoomsForOneUser : Fragment() {

    lateinit var recyclerviewroom : RoomResponseAdapter

    var gson: Gson = Gson()
    lateinit var shared: SharedPreferences
    var retriveuser: String = ""
    lateinit var shareEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Save Data in SharedPreferences
        shared = activity?.getSharedPreferences("Hotingo", Context.MODE_PRIVATE)!!
        shareEditor = shared.edit()

        //Get Data From SharedPreferences
        retriveuser = shared.getString("USER", "")
        var user = gson.fromJson(retriveuser, UserResponce::class.java)

        var view = inflater.inflate(R.layout.fragment_rooms_for_one_user, container, false)

        var userId: String = user.user?.id.toString()
        var token ="Bearer " + user.token

        var apiInterface: ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)

        var call: Call<ArrayList<RoomsResponse>> = apiInterface.getRoomForOneUser(token,userId)
        call.enqueue(object :Callback<ArrayList<RoomsResponse>>{
            override fun onFailure(call: Call<ArrayList<RoomsResponse>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ArrayList<RoomsResponse>>?, response: Response<ArrayList<RoomsResponse>>?
            ) {

                recyclerviewroom = RoomResponseAdapter(this@RoomsForOneUser)
                view.roomrecyclerview.layoutManager = LinearLayoutManager(context)
                view.roomrecyclerview.adapter = recyclerviewroom
                recyclerviewroom.setroomItem(response?.body()!!)
            }
        })

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            RoomsForOneUser().apply {

            }
    }
}
