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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ColorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        binding.btnNext.text="Expand Me"
        binding.btnNext.setOnClickListener {
            showMaxView()
           viewModel.destroyNextScreens(screenNumber+1)
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
        binding.btnNext.text="Next Step"
        binding.btnNext.setOnClickListener {
            showMiniViewOnScreen()
            viewModel.expandNextScreen(
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            ColorFragment().apply {
                arguments = bundle
            }
    }

}