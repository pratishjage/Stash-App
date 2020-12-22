package com.app.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.sampleapp.databinding.ActivityMainBinding
import com.app.sampleapp.stash.StashActivity
import com.app.sampleapp.stash.StashBuilder
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ONE
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.ui.ColorFragment
import com.app.sampleapp.ui.StepOneFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartStash.setOnClickListener {
            /*val intent = Intent(this, StashActivity::class.java)
            intent.putExtra(MAXIMUM_STEPS, 4)
            intent.putExtra(
                INITIAL_FRAGMENT,
                ColorFragment.javaClass.canonicalName?.removeSuffix(".Companion")
                    ?.let { className ->
                        ScreenDataModel(className, Bundle().apply {
                            putString(SCREEN_BG, "WHITE")
                            putInt(SCREEN_NUMBER, FRAGMENT_ONE)
                        })
                    }
            )
            startActivity(intent)
*/
            ColorFragment::class.qualifiedName?.let { it1 ->
                val build = StashBuilder.builder.setMaximumSteps(4)
                    .setInitialFragmentName(it1)
                    .setInitialFragmentBundle(Bundle().apply {
                        putString(SCREEN_BG, "WHITE")
                        putInt(SCREEN_NUMBER, FRAGMENT_ONE)
                    })
                    .build(this)

                startActivity(build)
            }
        }
    }
}