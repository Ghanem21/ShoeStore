package com.udacity.shoestore.screens.shoelist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private val _list = MutableLiveData<MutableList<Shoe>>()
    val list:LiveData<MutableList<Shoe>> = _list

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave:LiveData<Boolean> = _eventSave

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel:LiveData<Boolean> = _eventCancel
    init{
        _list.value = mutableListOf()
        _eventSave.value = false
        _eventCancel.value = false
    }

    fun add(shoe:Shoe){
        _list.value?.add(shoe)
    }

    fun onSave(){
        _eventSave.value = true
    }

    fun onSaveComplete(){
        _eventSave.value = false
    }

    fun onCancel(){
        _eventCancel.value = true
    }

    fun onCancelComplete(){
        _eventCancel.value = false
    }
}