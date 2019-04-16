package com.example.hotingo

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hotingo.Adapter.RoomImgAdapter
import com.example.hotingo.model.OrderOneRoom
import com.example.hotingo.model.ReservationRoom
import com.example.hotingo.model.RoomService
import com.example.hotingo.model.UserResponce
import com.example.hotingo.service.ApiClient
import com.example.hotingo.service.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_order_on_room.*
import kotlinx.android.synthetic.main.fragment_order_on_room.view.*
import kotlinx.android.synthetic.main.room_book_dialog.*
import kotlinx.android.synthetic.main.room_book_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderOnRoomFragment : Fragment() {

    lateinit var recyclerRoom :RoomImgAdapter
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

        //Save Data in SharedPreferences
        shared = activity?.getSharedPreferences("Hotingo", Context.MODE_PRIVATE)!!
        shareEditor = shared.edit()

        //Get Data From SharedPreferences
        retriveuser = shared.getString("USER", "")
        var user = gson.fromJson(retriveuser, UserResponce::class.java)


        var view = inflater.inflate(R.layout.fragment_order_on_room, container, false)

        var token ="Bearer " + user.token



        var apiInterface: ApiInterface = ApiClient.getUser().create(ApiInterface::class.java)
        var call :Call<RoomService> = token?.let { apiInterface.getRoomOrder(it, roomId) }!!
        call.enqueue(object :Callback<RoomService>{
            override fun onFailure(call: Call<RoomService>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RoomService>?, response: Response<RoomService>?) {

                room_number_ID.text=response?.body()?.number.toString()
                room_Desc_ID.text = response?.body()?.desc.toString()
                room_Price_ID.text = response?.body()?.price.toString()

                recyclerRoom = RoomImgAdapter(this@OrderOnRoomFragment,response?.body()!!)
                view.room_img_Recycler.layoutManager = GridLayoutManager(context,2)
                view.room_img_Recycler.adapter = recyclerRoom

            }
        })


       view.roomBookButton.setOnClickListener {

           val mDialogView = LayoutInflater.from(context).inflate(R.layout.room_book_dialog,null)

           val mBulider = AlertDialog.Builder(context).setView(mDialogView)

           val mAlertDialog = mBulider.show()

           mDialogView.roomBookDialogBTn.setOnClickListener {

               var mobileNumber :String = mDialogView.mobileNumberBook_ET.text.toString()
               var durationBook :String = mDialogView.DurationBook_ET.text.toString()

               var call: Call<ReservationRoom> = apiInterface.getReservationRoom(
                   OrderOneRoom(mobileNumber,durationBook), token, roomId)
               call.enqueue(object : Callback<ReservationRoom> {
                   override fun onFailure(call: Call<ReservationRoom>?, t: Throwable?) {
                   }

                   override fun onResponse(call: Call<ReservationRoom>?, response: Response<ReservationRoom>?) {


                       Toast.makeText(context , response?.body().toString() , Toast.LENGTH_LONG).show()

                   }
               })
               mAlertDialog.dismiss()
           }


        }

        return view
    }


    companion object {
        lateinit var roomId: String
        fun newInstance(id: String): OrderOnRoomFragment {
            roomId = id
            var fragment = OrderOnRoomFragment()
            return fragment
        }
    }
}
