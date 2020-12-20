package com.app.sampleapp.stash.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.vm.StashVM

abstract class StashBaseFragment : Fragment() {

    val viewModel by activityViewModels<StashVM>()


    abstract fun onExpanded()

    abstract fun showMiniView()

    abstract fun showMaxView()

    var screenPosition: Int = 0

    fun setCurrentScreenPosition(position: Int) {
        screenPosition = position;
    }

    protected fun expandNextScreen(
        screenDataModel: ScreenDataModel
    ) {
        viewModel.expandNextScreen(screenDataModel)
        showMiniView()
    }

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)


        listenToMiniState()

        //listenForExceptions()
    }

    private fun listenToMiniState() {
        viewModel.collapseState.observe(viewLifecycleOwner, {
            if (screenPosition == it) {
                showMiniView()
            }
            viewModel.collapseHandled()
        })
    }


}