package com.example.firebasecurdoperation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasecurdoperation.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.backBtnUA.setOnClickListener {
            val intent = Intent(this@UpdateActivity  , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // here we binding
        binding.UpdateBtnUA.setOnClickListener {
            val updateNumber = binding.updateNumber.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateOperator = binding.updateOperator.text.toString()
            val updateLocation = binding.updateLocation.text.toString()

            updateData(updateNumber,updateName,updateOperator,updateLocation)   // here we call function update data
        }

    }

    // here we create create function for update data
    private fun updateData(phone : String , name : String , operator : String , location : String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("phone Directory")

        val user = mapOf <String,String>("name" to name,"operator" to operator , "location" to location)
        databaseReference.child(phone).updateChildren(user).addOnSuccessListener {
            binding.updateNumber.text.clear()
            binding.updateName.text.clear()
            binding.updateLocation.text.clear()
            binding.updateOperator.text.clear()

            Toast.makeText(this, "Contact is Updated", Toast.LENGTH_SHORT).show()
        } .addOnFailureListener {
            Toast.makeText(this, "Unable To Update", Toast.LENGTH_SHORT).show()
        }
    }
}