package com.app.sampleapp.stash

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.sampleapp.databinding.ActivityStashBinding
import com.app.sampleapp.stash.vm.StashVM
import com.google.android.material.bottomsheet.BottomSheetBehavior

class StashActivity : AppCompatActivity() {

    val viewModel by viewModels<StashVM>()
    private lateinit var binding: ActivityStashBinding
    private val bottomSheetBehavior: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerTwo)
    }
    private val bottomSheetBehavior3: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerThree)
    }
    private val bottomSheetBehavior4: BottomSheetBehavior<FrameLayout> by lazy {
        BottomSheetBehavior.from(binding.containerFour)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStashBinding.inflate(layoutInflater)

        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.peekHeight = 0

        bottomSheetBehavior3.isHideable = true
        bottomSheetBehavior3.peekHeight = 0

        bottomSheetBehavior4.isHideable = true
        bottomSheetBehavior4.peekHeight = 0

        binding.expandButton.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.expandButton2.setOnClickListener {
            bottomSheetBehavior3?.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.expandButton3.setOnClickListener {
            bottomSheetBehavior4?.state = BottomSheetBehavior.STATE_EXPANDED
        }
        setContentView(binding.root)
    }
}