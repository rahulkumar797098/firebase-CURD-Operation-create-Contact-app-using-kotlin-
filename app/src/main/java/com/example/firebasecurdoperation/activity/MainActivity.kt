package com.example.firebasecurdoperation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasecurdoperation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

 // Go To Upload Activity
        binding.mainupload.setOnClickListener {
            val intent = Intent(this@MainActivity , UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Go to search Activity
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this@MainActivity , SearchActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.maiupdate.setOnClickListener {
            val intent  = Intent(this@MainActivity , UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.maindelete.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnAllContact.setOnClickListener {
            val intent = Intent(this@MainActivity , AllContactActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}