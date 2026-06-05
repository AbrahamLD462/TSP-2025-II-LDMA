package com.mexiti.catphotoapp.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Photo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexiti.catphotoapp.Model.CatPhoto
import com.mexiti.catphotoapp.Network.CatApi
import com.mexiti.catphotoapp.Network.CatApiService
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CatUIstate{
    data class Success(val photos:List<CatPhoto>): CatUIstate
    object Error:CatUIstate
    object Loading:CatUIstate
}

class CatViewModel:ViewModel(){
    var catUiState:CatUIstate by mutableStateOf(CatUIstate.Loading)
        private set

    init{
        getCatPhotos()
    }

    fun getCatPhotos(){
        viewModelScope.launch {
            catUiState = try {
                val listResult = CatApi.retrofitService.getPhotos()
                CatUIstate.Success( listResult )
            }catch(e:IOException) {
                CatUIstate.Error
            }

        }
    }
}

