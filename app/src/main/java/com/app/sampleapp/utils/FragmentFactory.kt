package com.app.sampleapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.sampleapp.stash.utils.Constants.SCREEN_BG
import com.app.sampleapp.ui.ColorFragment

object FragmentFactory {
    const val BUNDLE_KEY_TAG = "screenTag"
    const val RED_FRAGMENT = "red_fragment"
    const val YELLOW_FRAGMENT = "yellow_fragment"
    const val GREEN_FRAGMENT = "green_fragment"
    const val BLUE_FRAGMENT = "blue_fragment"

    fun getFragment(tag: String, bundle: Bundle?): Fragment {
        bundle?.apply { Bundle() }
        bundle!!.putString(BUNDLE_KEY_TAG, tag)
        return when (tag) {
            RED_FRAGMENT -> {
                bundle.putString(SCREEN_BG, "RED")
                ColorFragment.newInstance(bundle)
            }
            YELLOW_FRAGMENT -> {
                bundle.putString(SCREEN_BG, "YELLOW")
                ColorFragment.newInstance(bundle)
            }
            GREEN_FRAGMENT -> {
                bundle.putString(SCREEN_BG, "GREEN")
                ColorFragment.newInstance(bundle)
            }
            else -> {
                bundle.putString(SCREEN_BG, "BLUE")
                ColorFragment.newInstance(bundle)
            }
        }
    }
}