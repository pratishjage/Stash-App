package com.app.sampleapp.stash

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.app.sampleapp.R
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_TWO
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_FOUR
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_THREE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ONE
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS
import com.app.sampleapp.utils.FragmentFactory
import com.app.sampleapp.stash.vm.StashVM


class StashActivity : AppCompatActivity() {

    private val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private var MAXSCREENS: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MAXSCREENS = intent.getIntExtra(MAXIMUM_STEPS, 4)
        val initialScreen = intent.getParcelableExtra<ScreenDataModel>(INITIAL_FRAGMENT)

        viewModel.expandNextScreen(
            initialScreen
        )


        viewModel.stashState.observe(this, { screenData ->
            when (screenData.currentScreenPosition) {
                MAXSCREENS + 1 -> {
                    finish()
                }
                FRAGMENT_ONE -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containerOne,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                }
                FRAGMENT_TWO -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_two,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerTwo, true)
                }
                FRAGMENT_THREE -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerThree, true)
                }
                FRAGMENT_FOUR -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_four,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerFour, true)
                }
                else -> {
                    //return result from here
                    finish()
                }
            }
        })

        viewModel.destroyState.observe(this, { screenPosition ->
            var currentScreenPosition = screenPosition
            while (currentScreenPosition <= MAXSCREENS) {
                when (currentScreenPosition) {
                    FRAGMENT_TWO -> {
                        toggle(binding.containerTwo, false)
                    }
                    FRAGMENT_THREE -> {
                        toggle(binding.containerThree, false)
                    }
                    FRAGMENT_FOUR -> {
                        toggle(binding.containerFour, false)
                    }
                }
                currentScreenPosition++;
            }
        })
    }

    private fun toggle(view: View, show: Boolean) {
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 300
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(binding.root, transition)
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}