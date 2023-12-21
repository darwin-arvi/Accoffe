package com.fup.accoffe.ui.harvest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HarvestListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is HarvestListFragment"
    }
    val text: LiveData<String> = _text
}