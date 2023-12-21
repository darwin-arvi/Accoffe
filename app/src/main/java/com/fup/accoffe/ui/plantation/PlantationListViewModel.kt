package com.fup.accoffe.ui.plantation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlantationListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Plantation Fragment"
    }
    val text: LiveData<String> = _text
}