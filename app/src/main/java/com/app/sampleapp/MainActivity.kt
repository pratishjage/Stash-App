package com.app.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.sampleapp.databinding.ActivityMainBinding
import com.app.sampleapp.stash.StashBuilder
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ONE
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.ui.ColorFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnStartStash.setOnClickListener {
            ColorFragment::class.qualifiedName?.let { it1 ->
                val build = StashBuilder.builder.setMaximumSteps(4)
                    .setInitialFragmentName(it1)
                    .setInitialFragmentBundle(Bundle().apply {
                        putString(SCREEN_BG, "WHITE")
                        putInt(SCREEN_NUMBER, FRAGMENT_ONE)
                    })
                    .build(this)

                startActivityForResult(build, 5)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            5 -> {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "RESULT OK", Toast.LENGTH_SHORT).show()
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "RESULT CANCELED", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}