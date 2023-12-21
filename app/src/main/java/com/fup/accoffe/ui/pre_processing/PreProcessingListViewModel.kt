package com.fup.accoffe.ui.pre_processing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreProcessingListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is PreProcessingListFragment"
    }
    val text: LiveData<String> = _text
}