package com.app.sampleapp.stash.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.vm.StashVM

abstract class StashBaseFragment : Fragment() {

    val viewModel by activityViewModels<StashVM>()

    abstract fun onCollapsed()

    abstract fun onExpanded()

    private fun expandNextScreen(
        screenDataModel: ScreenDataModel
    ) {

    }
}