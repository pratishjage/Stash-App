package com.app.sampleapp.stash.model

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScreenDataModel(
    val fragmentName: String,
    val bundle: Bundle?
) : Parcelable