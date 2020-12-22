package com.app.sampleapp.stash.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.sampleapp.stash.model.StashDataModel

class StashVM : ViewModel() {

    private val _stashState: MutableLiveData<StashDataModel> by lazy { MutableLiveData<StashDataModel>() }
    val stashState: LiveData<StashDataModel> by lazy { _stashState }

    private val _destroyState: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val destroyState: LiveData<Int> by lazy { _destroyState }

    private val _localFragmentStack: MutableLiveData<Int> by lazy { MutableLiveData<Int>(1) }
    val localFragmentStack: LiveData<Int> by lazy { _localFragmentStack }

    fun destroyNextScreens(currentScreenPosition: Int) {
        resetLocalStack(currentScreenPosition - 1)
        _destroyState.postValue(currentScreenPosition)
    }

    fun expandNextScreen(stashDataModel: StashDataModel) {
        _stashState.postValue(stashDataModel)
        addToLocalStack()
    }

    private fun addToLocalStack() {
        val value = _localFragmentStack.value ?: 1
        _localFragmentStack.postValue(value + 1)
    }

    private fun resetLocalStack(screenIndex: Int) {
        _localFragmentStack.value = screenIndex
    }

    fun removeFromLocalStack() {
        val value = _localFragmentStack.value ?: 1
        _localFragmentStack.postValue(value - 1)
    }

    fun isLocalStackEmpty(): Boolean = localFragmentStack.value == 1

    fun getLocalStackCount(): Int = localFragmentStack.value ?: 1


}