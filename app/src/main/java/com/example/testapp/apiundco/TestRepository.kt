package com.example.testapp.apiundco

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData



class TestRepository(private val api: RassenApi) {

    private var _rassenWerter = MutableLiveData<List<Unterrasse>>()
    val rassenWerter: LiveData<List<Unterrasse>>
        get() = _rassenWerter


    suspend fun getRassen(){
        val result = api.retrofitService.getUnterrasse()
        _rassenWerter.postValue(result)
    }


}