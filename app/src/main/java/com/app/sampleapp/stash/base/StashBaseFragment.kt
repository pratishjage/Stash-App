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

    abstract fun showMiniView()

    abstract fun showExtendedView()

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


}