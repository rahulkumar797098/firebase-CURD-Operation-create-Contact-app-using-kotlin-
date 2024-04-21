package com.example.firebasecurdoperation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasecurdoperation.databinding.ActivitySearchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivitySearchBinding
    private lateinit var databaseReference :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.backBtnSA.setOnClickListener {
            val intent = Intent(this@SearchActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        /// here we apply set on click listener ... on search activity search btn
        binding.searchBtn.setOnClickListener {
            val searchPhone : String = binding.edtSearch.text.toString()
            if (searchPhone.isNotEmpty()){
                readDatabase(searchPhone)     // here we call function readDatabase
            }else{
                Toast.makeText(this, "please Enter Phone Number", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // here we create function for database
    private fun readDatabase(phone :String){
        databaseReference = FirebaseDatabase.getInstance().getReference("phone Directory")
        databaseReference.child(phone).get().addOnSuccessListener {

            if (it.exists()){
                val name = it.child("name").value
                val operator = it.child("operator").value
                val location = it.child("location").value
                val number = it.child("phone").value

                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()

                binding.edtSearch.text.clear()
                binding.readName.text = name.toString()
                binding.readOperator.text = operator.toString()
                binding.readLocation.text = location.toString()
                binding.readNumber.text = number.toString()
            }
            else{
                Toast.makeText(this, "Phone Number Does not exit ", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
    }
}