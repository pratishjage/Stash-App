package com.app.sampleapp.stash.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.app.sampleapp.stash.StashActivity
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.utils.Constants.INITIAL_FRAGMENT
import com.app.sampleapp.stash.utils.Constants.MAXIMUM_STEPS


class StashBuilder private constructor() {
    private var maximumSteps: Int? = null
    private var mFragmentBundle: Bundle? = null
    private var mFragmentName: String = ""

    private var mWithParameterFlags = 0
    fun setMaximumSteps(steps: Int?): StashBuilder {
        mWithParameterFlags = mWithParameterFlags or FLAG_MAXIMUM_STEPS
        maximumSteps = steps
        return this
    }

    fun setInitialFragmentBundle(fragmentData: Bundle): StashBuilder {
        mWithParameterFlags = mWithParameterFlags or FLAG_INITIAL_FRAGMENT_BUNDLE
        mFragmentBundle = fragmentData
        return this
    }

    fun setInitialFragmentName(fragmentName: String): StashBuilder {
        mWithParameterFlags = mWithParameterFlags or FLAG_INITIAL_FRAGMENT_NAME
        mFragmentName = fragmentName?.removeSuffix(".Companion")
        return this
    }

    fun build(ctx: Context?): Intent {
        val i = Intent(ctx, StashActivity::class.java)
        if (mWithParameterFlags and FLAG_MAXIMUM_STEPS != 0) i.putExtra(MAXIMUM_STEPS, maximumSteps)
        if ((mWithParameterFlags and FLAG_INITIAL_FRAGMENT_NAME != 0) and (mWithParameterFlags and FLAG_INITIAL_FRAGMENT_BUNDLE != 0)) i.putExtra(
            INITIAL_FRAGMENT,
            ScreenDataModel(mFragmentName, mFragmentBundle)
        )
        return i
    }

    companion object {
        private const val FLAG_MAXIMUM_STEPS = 1
        private const val FLAG_INITIAL_FRAGMENT_NAME = 2
        private const val FLAG_INITIAL_FRAGMENT_BUNDLE = 3

        val builder: StashBuilder
            get() = StashBuilder()
    }
}
