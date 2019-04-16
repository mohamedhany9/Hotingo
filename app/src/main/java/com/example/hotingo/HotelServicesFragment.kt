package com.example.hotingo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotingo.Adapter.HotelServiceAdapter
import com.example.hotingo.model.UserResponce
import com.example.hotingo.model.UserServiceRespone
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_hotel_services.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HotelServicesFragment : Fragment() {
    lateinit var recyclerAdapter: HotelServiceAdapter

    var gson: Gson = Gson()
    lateinit var shared: SharedPreferences
    var retriveuser: String = ""
    lateinit var shareEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_hotel_services, container, false)


        //Save Data in SharedPreferences
        shared = activity?.getSharedPreferences("Hotingo", Context.MODE_PRIVATE)!!
        shareEditor = shared.edit()

        //Get Data From SharedPreferences
        retriveuser = shared.getString("USER", "")
        var user = gson.fromJson(retriveuser, UserResponce::class.java)


        recyclerAdapter = HotelServiceAdapter(this@HotelServicesFragment)
        view.recyclerview.layoutManager = LinearLayoutManager(context)
        view.recyclerview.adapter = recyclerAdapter

        var token = "Bearer " + user.token
        var apiInterface: ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)
        var call: Call<ArrayList<UserServiceRespone>> = token?.let { apiInterface.getService(it) }!!

        call.enqueue(object : Callback<ArrayList<UserServiceRespone>> {
            override fun onFailure(call: Call<ArrayList<UserServiceRespone>>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<ArrayList<UserServiceRespone>>?,
                response: Response<ArrayList<UserServiceRespone>>?
            ) {

                recyclerAdapter.setserviceItem(response?.body()!!)

             // Toast.makeText(this@HotelServicesFragment.context, "" + response?.body(), Toast.LENGTH_LONG).show()
//              Toast.makeText(this@MainActivity, "" + response?.errorBody().toString(), Toast.LENGTH_LONG).show()

            }
        })
        return view

    }


    companion object {
        fun newInstance(): HotelServicesFragment {
            val fragment = HotelServicesFragment()
            return fragment
        }

    }
}
