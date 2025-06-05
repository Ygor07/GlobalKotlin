package com.example.checkpoint3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpoint3.databinding.StartBinding
import com.google.gson.Gson

class Start : AppCompatActivity() {

    private lateinit var binding: StartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val startButton : Button = binding.button
        startButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}