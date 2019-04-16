package com.example.hotingo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hotingo.HotelServicesFragment
import com.example.hotingo.R
import com.example.hotingo.model.UserServiceRespone

class HotelServiceAdapter(val context: HotelServicesFragment): RecyclerView.Adapter<HotelServiceAdapter.MyViewHolder>() {

    var serviceItem :List<UserServiceRespone> = listOf()



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.service_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return serviceItem.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.serviceTextTV.text = serviceItem.get(position).name
        Glide.with(context).load(serviceItem.get(position).img)
            .into(holder.image)

        holder.serviceDescTV.text = serviceItem.get(position).desc

    }

    fun setserviceItem(serviceItem: List<UserServiceRespone>){
        this.serviceItem = serviceItem;
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val serviceDescTV :TextView = itemView?.findViewById(R.id.serviceDescET)
        val serviceTextTV:TextView = itemView?.findViewById(R.id.serviceTextTV)
        val image: ImageView = itemView?.findViewById(R.id.ServiceImg)
    }
}