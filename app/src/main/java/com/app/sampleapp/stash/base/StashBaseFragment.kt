package com.app.sampleapp.stash.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.model.StashDataModel
import com.app.sampleapp.stash.vm.StashVM

abstract class StashBaseFragment : Fragment() {

    private val viewModel by activityViewModels<StashVM>()

    /**
     * Render Collapsed View in This Method
     */
    abstract fun showMiniView()

    /**
     * Render Extended View in This Method
     */
    abstract fun showExtendedView()

    /**
     * return Position for this Screen
     * Required value must be same or below maxScreens Value passed in StashActivity
     */
    abstract val currentScreenPosition: Int

    /**
     * Method To Expand Current Screen and Remove all next Screens
     */
    protected fun destroyNextScreens() {
        extendCurrentScreen()
        viewModel.destroyNextScreens(currentScreenPosition + 1)
    }


    /**
     * Method To Expand next Screen
     * and Minimize current Screen
     */
    protected fun expandNextScreen(
        nextFragment: Fragment
    ) {
        viewModel.expandNextScreen(StashDataModel(currentScreenPosition + 1, nextFragment))
        showMiniView()
    }

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        extendCurrentScreen()
        listenToLoadingState()

    }

    @CallSuper
    private fun listenToLoadingState() {
        viewModel.localFragmentStack.observe(viewLifecycleOwner, {
            if (it == currentScreenPosition) {
                extendCurrentScreen()
            }
        })
    }

    @CallSuper
    private fun extendCurrentScreen() {
        showExtendedView()
    }


}