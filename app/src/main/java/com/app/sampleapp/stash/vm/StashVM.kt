package com.app.sampleapp.stash.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.sampleapp.stash.model.ScreenDataModel

class StashVM : ViewModel() {

    protected val _stashState: MutableLiveData<ScreenDataModel> by lazy { MutableLiveData<ScreenDataModel>() }
    val stashState: LiveData<ScreenDataModel> by lazy { _stashState }


    protected val _destroyState: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val destroyState: LiveData<Int> by lazy { _destroyState }

    fun updateScreenState() {

    }

    fun expandNextScreen(screenDataModel: ScreenDataModel) {
        _stashState.postValue(screenDataModel)
    }

    fun destroyNextScreens(currentScreenPosition: Int) {
        _destroyState.postValue(currentScreenPosition)
    }

}