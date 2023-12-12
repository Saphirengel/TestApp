package com.example.testapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.apiundco.RassenApi
import com.example.testapp.apiundco.TestRepository
import kotlinx.coroutines.launch

class TestViewModel(application: Application): AndroidViewModel(application) {


    private val repository = TestRepository(RassenApi)

    val rassen = repository.rassenWerter

    fun getRasse(){
        viewModelScope.launch{
            repository.getRassen()}
    }
}


