package com.app.sampleapp.stash

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.sampleapp.R
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.stash.utils.FragmentFactory
import com.app.sampleapp.stash.vm.StashVM
import com.google.android.material.bottomsheet.BottomSheetBehavior

class StashActivity : AppCompatActivity() {

    val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private val bottomSheetBehavior2: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerTwo)
    }
    private val bottomSheetBehavior3: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerThree)
    }
    private val bottomSheetBehavior4: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerFour)
    }

    val maxScreens = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)

        bottomSheetBehavior2.isHideable = true
        bottomSheetBehavior2.peekHeight = 0
        bottomSheetBehavior2.isDraggable=false

        bottomSheetBehavior3.isHideable = true
        bottomSheetBehavior3.peekHeight = 0
        bottomSheetBehavior3.isDraggable=false


        bottomSheetBehavior4.isHideable = true
        bottomSheetBehavior4.peekHeight = 0
        bottomSheetBehavior4.isDraggable=false


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
                    bottomSheetBehavior2?.state = BottomSheetBehavior.STATE_EXPANDED
                }
                2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_three,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    bottomSheetBehavior3?.state = BottomSheetBehavior.STATE_EXPANDED
                }
                3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_four,
                        FragmentFactory.getFragment(screenData.nextScreenTag, screenData.bundle)
                    ).commit()
                    bottomSheetBehavior4?.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        })

        viewModel.destroyState.observe(this, { screenPosition ->
            var currentScreenPosition = screenPosition
            while (currentScreenPosition <= maxScreens) {
                if (currentScreenPosition == 0) {

                } else if (currentScreenPosition == 1) {
                    bottomSheetBehavior2?.state = BottomSheetBehavior.STATE_COLLAPSED
                } else if (currentScreenPosition == 2) {
                    bottomSheetBehavior3?.state = BottomSheetBehavior.STATE_COLLAPSED

                } else if (currentScreenPosition == 3) {
                    bottomSheetBehavior4?.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                currentScreenPosition++;
            }
        })
        setContentView(binding.root)
    }
}