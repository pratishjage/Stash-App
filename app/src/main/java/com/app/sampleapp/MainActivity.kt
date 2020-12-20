package com.app.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.sampleapp.databinding.ActivityMainBinding
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.StashActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStartStash.setOnClickListener {
            startActivity(Intent(this, StashActivity::class.java))
        }
    }
}