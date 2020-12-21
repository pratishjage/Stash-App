package com.app.sampleapp.stash.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ReportFragment
import androidx.lifecycle.ViewModel
import com.app.sampleapp.stash.model.ScreenDataModel
import com.app.sampleapp.stash.model.StashDataModel

class StashVM : ViewModel() {

    protected val _stashState: MutableLiveData<StashDataModel> by lazy { MutableLiveData<StashDataModel>() }
    val stashState: LiveData<StashDataModel> by lazy { _stashState }


    protected val _destroyState: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val destroyState: LiveData<Int> by lazy { _destroyState }

    fun updateScreenState() {

    }

    /*fun expandNextScreen(screenDataModel: ScreenDataModel) {
        _stashState.postValue(screenDataModel)
    }*/

    fun destroyNextScreens(currentScreenPosition: Int) {
        _destroyState.postValue(currentScreenPosition)
    }

    fun expandNextScreen(stashDataModel: StashDataModel) {
        _stashState.postValue(stashDataModel)
    }

}