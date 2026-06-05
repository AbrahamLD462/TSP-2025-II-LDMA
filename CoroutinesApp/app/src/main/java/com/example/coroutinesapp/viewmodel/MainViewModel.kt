package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var resultState by mutableStateOf(
        " "
    )
        private set

    var countTime by mutableStateOf(
        0
    )
        private set

    var countTime2 by mutableStateOf(
        0
    )
        private set

    private var oneCount by mutableStateOf(false)
    private var twoCount by mutableStateOf(false)
    private var job: Job? = null
    private var job2: Job? = null

    fun fetchData(){
        countTime = 0
        countTime2 = 0
        resultState = " "
        job = viewModelScope.launch {
            for (i in 1..5){
                delay(1000)
                countTime = i
            }
            oneCount = true
            count2()
            text()
        }

//        if (oneCount){
//            job?.cancel()
//        }
    }

    fun text(){
        viewModelScope.launch {
            resultState = "Respuesta obtenida de la web"
        }
    }

    fun count2(){
        job2 = viewModelScope.launch {
            for (i in 1..5){
                delay(1000)
                countTime2 = i
            }
            twoCount = true
        }
    }

    fun suspend(){
        job?.cancel()
        job2?.cancel()
    }

    //esta función bloquea el hilo principal Thread
    fun bloqueApp(){
        Thread.sleep(5000)
        resultState = "Respuesta obtenida de la web"
    }
}