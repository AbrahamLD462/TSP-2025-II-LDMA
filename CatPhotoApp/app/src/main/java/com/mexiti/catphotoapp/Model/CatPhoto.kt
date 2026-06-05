package com.mexiti.catphotoapp.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatPhoto (
    @SerialName("id") val id:String,
    val url:String,
    val width:Int,
    val height:Int
)