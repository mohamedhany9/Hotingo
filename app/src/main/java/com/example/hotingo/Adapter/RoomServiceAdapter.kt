package com.example.hotingo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hotingo.R
import com.example.hotingo.RoomServiceFragment
import com.example.hotingo.model.RoomService

class RoomServiceAdapter(val context: RoomServiceFragment,var listner :OnClickListner )
                                                        : RecyclerView.Adapter<RoomServiceAdapter.MyViewHolder>() {

    var roomItem: List<RoomService> = listOf()


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.room_service_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomItem.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        with(holder.itemView) {
            holder.roomnumberTv.text = roomItem.get(position).number
            Glide.with(context).load(roomItem.get(position).imgs?.get(0))
                .into(holder.imageRoom)

            setOnClickListener {
                listner.onServiceClick(roomItem.get(position).id!!)

            }
        }


    }

    interface OnClickListner {
        fun onServiceClick(id: String)
    }

    fun setroomItem(roomItem: List<RoomService>) {
        this.roomItem = roomItem;
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomnumberTv: TextView = itemView?.findViewById(R.id.roomNumberTV)
        val imageRoom: ImageView = itemView?.findViewById(R.id.RoomServiceImg)
    }
}