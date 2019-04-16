package com.example.hotingo


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotingo.Adapter.RoomServiceAdapter
import com.example.hotingo.model.RoomService
import com.example.hotingo.model.UserResponce
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_room_service.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RoomServiceFragment : Fragment() {


    lateinit var recyclerRoom: RoomServiceAdapter
    var gson: Gson = Gson()
    lateinit var shared: SharedPreferences
    var retriveuser: String = ""
    lateinit var shareEditor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        //Save Data in SharedPreferences
        shared = activity?.getSharedPreferences("Hotingo", Context.MODE_PRIVATE)!!
        shareEditor = shared.edit()

        //Get Data From SharedPreferences
        retriveuser = shared.getString("USER", "")
        var user = gson.fromJson(retriveuser, UserResponce::class.java)

        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_room_service, container, false)

        recyclerRoom = RoomServiceAdapter(this@RoomServiceFragment, object : RoomServiceAdapter.OnClickListner {
            override fun onServiceClick(id: String) {
                var fragment = OrderOnRoomFragment.newInstance(id)
                //replace fragment to fragment
                var fragmentManager: FragmentManager = activity?.supportFragmentManager!!
                fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit()
            }

        })
        view.recyclerview.layoutManager = GridLayoutManager(context, 2)
        view.recyclerview.adapter = recyclerRoom

        var token = "Bearer " + user.token
        var apiInterface: ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)
        var call: Call<ArrayList<RoomService>> = token?.let { apiInterface.getRoomsService(it) }!!
        call.enqueue(object : Callback<ArrayList<RoomService>> {
            override fun onFailure(call: Call<ArrayList<RoomService>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ArrayList<RoomService>>?, response: Response<ArrayList<RoomService>>?) {

                recyclerRoom.setroomItem(response?.body()!!)
            }
        })
        return view
    }


    companion object {
        fun newInstance(): RoomServiceFragment {
            val fragment = RoomServiceFragment()
            return fragment
        }
    }
}
