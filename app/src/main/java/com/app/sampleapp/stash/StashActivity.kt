package com.app.sampleapp.stash

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.app.sampleapp.R
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ONE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_THREE
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_TWO
import com.app.sampleapp.stash.utils.Constants.FRAGMENT_ZERO
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.stash.utils.FragmentFactory
import com.app.sampleapp.stash.vm.StashVM


class StashActivity : AppCompatActivity() {

    private val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private val MAX_SCREENS = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.expandNextScreen(
            ScreenDataModel(
                FRAGMENT_ZERO,
                FragmentFactory.RED_FRAGMENT,
                Bundle().apply {
                    putString(SCREEN_BG, "RED")
                    putInt(SCREEN_NUMBER, FRAGMENT_ZERO)
                })
        )
        viewModel.stashState.observe(this, { screenData ->
            when (screenData.currentScreenPosition) {
                FRAGMENT_ZERO -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containerOne,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                }
                FRAGMENT_ONE -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_two,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerTwo, true)
                }
                FRAGMENT_TWO -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerThree, true)
                }
                FRAGMENT_THREE -> {
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
            while (currentScreenPosition <= MAX_SCREENS) {
                when (currentScreenPosition) {
                    FRAGMENT_ONE -> {
                        toggle(binding.containerTwo, false)
                    }
                    FRAGMENT_TWO -> {
                        toggle(binding.containerThree, false)
                    }
                    FRAGMENT_THREE -> {
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