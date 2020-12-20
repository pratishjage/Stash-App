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

    abstract val screenPosition: Int


    protected fun expandNextScreen(
        screenDataModel: ScreenDataModel
    ) {
        viewModel.expandNextScreen(screenDataModel)
        showMiniView()
    }

    protected fun destroyNextScreens(){
        viewModel.destroyNextScreens(screenPosition+1)
    }


}