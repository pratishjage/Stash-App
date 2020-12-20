package com.app.sampleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sampleapp.databinding.FragmentRedBinding
import com.app.sampleapp.stash.base.StashBaseFragment
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.utils.FragmentFactory.YELLOW_FRAGMENT

class ColorFragment : StashBaseFragment() {
    private var screenColor: String? = null
    private var screenNumber: Int = 0

    private var _binding: FragmentRedBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            screenColor = it.getString(SCREEN_BG)
            screenNumber = it.getInt(SCREEN_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showExtendedView()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showMiniViewOnScreen() {
        binding.tvTitle.text = "I am collapsed $screenColor -----  $screenNumber"
        binding.btnNext.setOnClickListener(null)
        binding.btnNext.text = "Expand Me"
        binding.btnNext.setOnClickListener {
            showExtendedView()
            destroyNextScreens()
        }
    }

    override fun showMiniView() {
        showMiniViewOnScreen()
    }

    override fun showExtendedView() {
        binding.tvTitle.text = screenColor
        binding.btnNext.setOnClickListener(null)
        binding.btnNext.text = "Next Step"
        binding.btnNext.setOnClickListener {
            expandNextScreen(
                ScreenDataModel(
                    screenNumber + 1,
                    YELLOW_FRAGMENT,
                    Bundle().apply {
                        putString(SCREEN_BG, "RED")
                        putInt(SCREEN_NUMBER, screenNumber + 1)
                    })
            )
        }
    }

    override var screenPosition: Int
        get() = screenNumber
        set(value) {}

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            ColorFragment().apply {
                arguments = bundle
            }
    }

}