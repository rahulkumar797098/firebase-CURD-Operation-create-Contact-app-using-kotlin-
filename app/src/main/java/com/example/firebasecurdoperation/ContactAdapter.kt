package com.example.firebasecurdoperation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val context: Context , private val contactListData : List<UserDataModel>) :RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactListData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.nameContact.text = contactListData[position].name
        holder.operatorContact.text = contactListData[position].operator
        holder.locationContact.text = contactListData[position].location
        holder.numberContact.text = contactListData[position].phone
    }
}

class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

    var nameContact : TextView
    var numberContact : TextView
    var locationContact : TextView
    var operatorContact : TextView

    init {
        nameContact = itemView.findViewById(R.id.nameContact)
        numberContact = itemView.findViewById(R.id.numberContact)
        locationContact = itemView.findViewById(R.id.locationContact)
        operatorContact = itemView.findViewById(R.id.operatorContact)
    }

}
