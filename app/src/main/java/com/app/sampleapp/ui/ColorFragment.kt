package com.app.sampleapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.activityViewModels
import com.app.sampleapp.R
import com.app.sampleapp.databinding.FragmentRedBinding
import com.app.sampleapp.stash.base.StashBaseFragment
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.stash.utils.Constants.SCREEN_NUMBER
import com.app.sampleapp.stash.utils.FragmentFactory.YELLOW_FRAGMENT
import com.app.sampleapp.stash.vm.StashVM

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


    private fun showMiniViewOnScreen() {
        binding.tvTitle.text = "I am collapsed $screenColor -----  $screenNumber"
        binding.btnNext.setOnClickListener(null)
        binding.btnNext.text = "Expand Me"
        binding.btnNext.setOnClickListener {
            showMaxView()
            viewModel.destroyNextScreens(screenNumber + 1)
        }
    }

    override fun onExpanded() {

    }

    override fun showMiniView() {
        showMiniViewOnScreen()
    }

    override fun showMaxView() {
        binding.tvTitle.text = screenColor
        binding.btnNext.setOnClickListener(null)
        binding.btnNext.text = "Next Step"
        binding.btnNext.setOnClickListener {
            expandNextScreen( ScreenDataModel(
                screenNumber + 1,
                YELLOW_FRAGMENT,
                Bundle().apply {
                    putString(SCREEN_BG, "RED")
                    putInt(SCREEN_NUMBER, screenNumber + 1)
                }))
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
        showMaxView()
        //viewModel.collapsePreviousScreen(screenNumber - 1)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            ColorFragment().apply {
                arguments = bundle
            }
    }

}