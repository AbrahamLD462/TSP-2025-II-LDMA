package com.example.duckapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.duck_ejercicio7_ldma.ui.theme.Duck_Ejercicio7_LDMATheme
import com.example.duckapp.ui.DuckScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Duck_Ejercicio7_LDMATheme {
                DuckScreen()
            }
        }
    }
}
