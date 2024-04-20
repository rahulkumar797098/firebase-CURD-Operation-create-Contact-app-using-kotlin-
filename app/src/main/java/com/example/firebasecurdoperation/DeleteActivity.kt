package com.example.firebasecurdoperation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasecurdoperation.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.backBtnDA.setOnClickListener {
            val intent = Intent(this@DeleteActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // here we apply set on click listener on delete button
        binding.btnDeleteDA.setOnClickListener {
            val phone = binding.edtDeletePhone.text.toString()
            if (phone.isNotEmpty()){
                deleteData(phone)     // here we call function deleteData
            }else {
                Toast.makeText(this, "Please Enter Phone NUmber", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // here we create function for delete number
    private fun deleteData(phone : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("phone Directory")
        databaseReference.child(phone).removeValue().addOnSuccessListener {
            binding.edtDeletePhone.text.clear()

            Toast.makeText(this, "Contact is Successfully Deleted", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Can't Deleted", Toast.LENGTH_SHORT).show()
            }
    }
}