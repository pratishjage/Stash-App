package com.app.sampleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sampleapp.databinding.FragmentRedBinding
import com.app.sampleapp.stash.base.StashBaseFragment
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.utils.AppUtils

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
        val randomColor = AppUtils.getRandomColor()
        binding.rlMini.setBackgroundColor(randomColor)
        binding.rlMax.setBackgroundColor(randomColor)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showMiniViewOnScreen() {
        binding.rlMax.visibility = View.GONE
        binding.rlMini.visibility = View.VISIBLE
        binding.tvTitleMini.text = "COLLAPSED VIEW"
        binding.rlMini.setOnClickListener {
            destroyNextScreens()
        }
    }

    override fun showMiniView() {
        showMiniViewOnScreen()
    }

    override fun showExtendedView() {
        binding.rlMax.visibility = View.VISIBLE
        binding.rlMini.visibility = View.GONE
        binding.tvTitle.text = "EXPANDED VIEW"
        binding.btnNext.text = "Next Step"
        binding.btnNext.setOnClickListener {
            expandNextScreen(ColorFragment.newInstance(Bundle().apply {
                putString(SCREEN_BG, "RED")
                putInt(SCREEN_NUMBER, screenNumber + 1)
            }))
        }
    }

    override var currentScreenPosition: Int
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