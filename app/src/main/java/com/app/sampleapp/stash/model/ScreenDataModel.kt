package com.app.sampleapp.stash.model

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScreenDataModel(
    val currentScreenPosition: Int,
    val nextScreenTag: String,
    val bundle: Bundle?
) : Parcelable