package com.example.duckapp.model

import kotlinx.serialization.Serializable

@Serializable
data class DuckPhoto(
    val url: String,
    val message: String
)
