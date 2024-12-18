package com.example.gestortareas.View.PanelTareas



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PanelTareas(
    onViewTasks: () -> Unit,
    onCreateTask: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onViewTasks,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Tareas Activas")
        }
        ElevatedButton(
            onClick = onCreateTask,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Nueva Tarea")
        }
    }
}
