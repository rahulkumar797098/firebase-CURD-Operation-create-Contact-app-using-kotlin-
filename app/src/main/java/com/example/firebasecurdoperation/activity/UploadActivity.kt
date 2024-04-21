package com.example.firebasecurdoperation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasecurdoperation.UserDataModel
import com.example.firebasecurdoperation.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back Button
        binding.backBtnUA.setOnClickListener {
            val  intent = Intent(this@UploadActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }


       // here we apply on click listner
        binding.btnSave.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val operator = binding.uploadOperator.text.toString()
            val location = binding.uploadLocation.text.toString()
            val phone = binding.uploadNumber.text.toString()

            //
            databaseReference = FirebaseDatabase.getInstance().getReference("phone Directory")   // table name

            val users = UserDataModel(name , operator , location , phone )

            databaseReference.child(phone).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadOperator.text.clear()
                binding.uploadLocation.text.clear()
                binding.uploadNumber.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity , MainActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}