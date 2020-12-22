package com.app.sampleapp.utils

import android.graphics.Color
import android.view.View
import java.util.*

object AppUtils {
     fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}