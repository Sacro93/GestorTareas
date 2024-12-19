package com.example.gestortareas.View.HomeScreen

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gestortareas.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestortareas.ViewModel.HomeScreenViewModel.HomeScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onSeeProfile: () -> Unit,
    onLogout: () -> Unit,
    onViewTasks: () -> Unit,
    onCreateTask: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inicio") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Sección del usuario
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Juan Pérez", style = MaterialTheme.typography)
            }

            // Botones de acción
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {}) {
                    Text("Crear Tarea")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {}) {
                    Text("Ver Tareas")
                }
            }

            // Tarjeta de tarea próxima (vacía por ahora)
            Spacer(modifier = Modifier.height(16.dp))
            item {
    MyBasicCard()
}
        }
    }
}

@Composable
fun MyBasicCard() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.next_task),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
    }
}