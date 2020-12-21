package com.app.sampleapp.stash.model

import android.os.Bundle
import androidx.fragment.app.Fragment

data class StashDataModel(
    val currentScreenPosition: Int,
    val fragment: Fragment
)