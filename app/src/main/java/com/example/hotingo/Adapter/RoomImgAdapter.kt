package com.example.hotingo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.hotingo.OrderOnRoomFragment
import com.example.hotingo.R
import com.example.hotingo.model.RoomService

class RoomImgAdapter (val context: OrderOnRoomFragment,var roomImgItem :RoomService): RecyclerView.Adapter<RoomImgAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.room_img_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomImgItem.imgs?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


       Glide.with(context).load(roomImgItem.imgs?.get(position))
            .into(holder.image)


    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {

        var image: ImageView = itemView?.findViewById(R.id.roomIV)
    }
}