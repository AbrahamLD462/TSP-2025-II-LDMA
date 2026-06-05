package com.example.duckapp.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duckapp.viewmodel.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.duckapp.R

@Composable
fun DuckScreen(viewModel: DuckViewModel = viewModel()) {
    when (val state = viewModel.uiState) {
        is DuckUIState.Loading -> LoadingScreen()
        is DuckUIState.Error -> ErrorScreen { viewModel.fetchDucks() }
        is DuckUIState.Success -> JsonText(state.json)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.duck_loading),
            contentDescription = "Cargando patos...",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Cargando patos...", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.duck_error),
            contentDescription = "Error al cargar",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Error al cargar los patos ", textAlign = TextAlign.Center)
        Button(onClick = onRetry, modifier = Modifier.padding(top = 8.dp)) {
            Text("Reintentar")
        }
    }
}

@Composable
fun JsonText(json: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen encabezado
        Image(
            painter = painterResource(R.drawable.duck_success),
            contentDescription = "Patos cargados",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 8.dp)
        )

        // Título
        Text(
            text = " DuckApp ",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        // JSON como texto
        Text(
            text = json,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

