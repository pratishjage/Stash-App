package com.app.sampleapp.stash.model

import android.os.Bundle

data class ScreenDataModel(
    val currentScreenPosition: Int,
    val nextScreenTag: String,
    val bundle: Bundle?
)