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
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.stash.utils.FragmentFactory
import com.app.sampleapp.stash.vm.StashVM


class StashActivity : AppCompatActivity() {

    private val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private val maxScreens = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)
        viewModel.expandNextScreen(
            ScreenDataModel(
                0,
                FragmentFactory.RED_FRAGMENT,
                Bundle().apply {
                    putString(SCREEN_BG, "RED")
                    putInt(SCREEN_NUMBER, 0)
                })
        )
        viewModel.stashState.observe(this, { screenData ->
            when (screenData.currentScreenPosition) {
                0 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containerOne,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_two,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerTwo, true)
                }
                2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    toggle(binding.containerThree, true)
                }
                3 -> {
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
            while (currentScreenPosition <= maxScreens) {
                when (currentScreenPosition) {
                    1 -> {
                        toggle(binding.containerTwo, false)
                    }
                    2 -> {
                        toggle(binding.containerThree, false)

                    }
                    3 -> {
                        toggle(binding.containerFour, false)

                    }
                }
                currentScreenPosition++;
            }
        })
        setContentView(binding.root)
    }

    private fun toggle(view: View, show: Boolean) {
        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 300
        transition.addTarget(view)
        TransitionManager.beginDelayedTransition(binding.root, transition)
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}