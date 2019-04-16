package com.example.hotingo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hotingo.R
import com.example.hotingo.RoomsForOneUser
import com.example.hotingo.model.RoomsResponse

class RoomResponseAdapter(val context: RoomsForOneUser) : RecyclerView.Adapter<RoomResponseAdapter.MyViewHolder>() {

    var roomItem: List<RoomsResponse> = listOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.room_user_item, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomItem.size
    }
    fun setroomItem(roomItem: List<RoomsResponse>){
        this.roomItem = roomItem;
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.roomNumber.text = roomItem.get(position).room?.number.toString()
        holder.roomDescription.text = roomItem.get(position).room?.desc.toString()
        holder.roomDuration.text = roomItem.get(position).duration.toString()
        holder.roomPrice.text = roomItem.get(position).room?.price.toString()

        Glide.with(context).load(roomItem.get(position).room?.imgs?.get(0)).into(holder.roomImg)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomImg: ImageView = itemView.findViewById(R.id.roomImg)
        val roomNumber: TextView = itemView.findViewById(R.id.roomNumber)
        val roomDescription: TextView = itemView.findViewById(R.id.roomDescription)
        val roomDuration: TextView = itemView.findViewById(R.id.roomDuration)
        val roomPrice: TextView = itemView.findViewById(R.id.roomPrice)

    }
}