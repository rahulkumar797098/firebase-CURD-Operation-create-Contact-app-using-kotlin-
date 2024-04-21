package com.example.firebasecurdoperation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebasecurdoperation.ContactAdapter
import com.example.firebasecurdoperation.UserDataModel
import com.example.firebasecurdoperation.databinding.ActivityAllContactBinding


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllContactBinding
    private lateinit var contactList : ArrayList<UserDataModel>
    private lateinit var adapter: ContactAdapter
    private  var databaseReference: DatabaseReference? = null
    private  var eventListener : ValueEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = com.example.firebasecurdoperation.databinding.ActivityAllContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.backBtnAC.setOnClickListener {
            val intent = Intent(this@AllContactActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        val grideLayoutManager = GridLayoutManager(this@AllContactActivity,1)
        binding.recyclerViewContact.layoutManager = grideLayoutManager
        contactList = ArrayList()
        adapter = ContactAdapter(this@AllContactActivity ,contactList)
        binding.recyclerViewContact.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("phone Directory")
        eventListener = databaseReference!!.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                for (itemSnapshot in snapshot.children){
                    val UserDataModel = itemSnapshot.getValue(UserDataModel::class.java)
                    if (UserDataModel != null){
                        contactList.add(UserDataModel)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}