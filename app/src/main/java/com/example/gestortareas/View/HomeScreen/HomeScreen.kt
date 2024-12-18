package com.example.gestortareas.View.HomeScreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LinkOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestortareas.View.PanelTareas.RecentTaskSection
import com.example.gestortareas.ViewModel.HomeScreenViewModel


import androidx.compose.material.icons.filled.Logout




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onSeeProfile: () -> Unit,
    onLogout: () -> Unit,
    onViewTasks: () -> Unit,
    onCreateTask: () -> Unit
) {
    val recentTask by viewModel.recentTask.collectAsState()
    Scaffold (topBar = {
        TopAppBar(title = { Text("Inicio") },
            actions = {
                IconButton(onClick = onSeeProfile) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Ver Perfil"
                    )
                }
                IconButton (onClick = onLogout) {
                    Icon(
                        imageVector = Icons.Default.LinkOff,
                        contentDescription = "Cerrar Sesión"
                    )
                }
            })
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                if (recentTask != null) {
                    RecentTaskSection(
                        title = recentTask!!.name,
                        description = recentTask!!.description,
                        dateTime = recentTask!!.dateTime,
                        imageUrl = recentTask!!.imageUrl
                    )
                } else {
                    Text(
                        text = "No hay tareas recientes.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            item { Button(onClick = onViewTasks) { Text("Ver Tareas") } }
            item {
                Button(onClick = onCreateTask) {
                    Text(
                        "Crear Nueva Tarea"
                    )
                }
            }
        }
    }
}