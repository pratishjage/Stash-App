package com.app.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.sampleapp.databinding.ActivityMainBinding
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.StashActivity
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ZERO
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.stash.utils.FragmentFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStartStash.setOnClickListener {
            val intent = Intent(this, StashActivity::class.java)
            intent.putExtra(MAXIMUM_STEPS, 3)
            intent.putExtra(
                INITIAL_FRAGMENT,
                ScreenDataModel(0, FragmentFactory.YELLOW_FRAGMENT, Bundle().apply {
                    putString(SCREEN_BG, "RED")
                    putInt(SCREEN_NUMBER, FRAGMENT_ZERO)
                })
            )
            startActivity(intent)
        }
    }
}