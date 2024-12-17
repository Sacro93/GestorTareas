package com.example.gestortareas.View.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestortareas.R


//El código se ha dividido en funciones composables más pequeñas y manejables:
// UserProfileSection, TaskActionsSection y RecentTaskSection.

//Se han añadido descripciones de contenido a los elementos AsyncImage
// e IconButton para mejorar la accesibilidad.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onEditProfile: () -> Unit,
    onLogout: () -> Unit,
    onViewTasks: () -> Unit,
    onCreateTask: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tus Tareas") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Perfil del usuario
            PerfilUsuario(
                userName = "John Doe",
                onEditProfile = onEditProfile,
                onLogout = onLogout
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Panel de tareas
            PanelTareas(
                onViewTasks = onViewTasks,
                onCreateTask = onCreateTask
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de tareas recientes
            RecentTaskSection(
                recentTask = "Comprar leche"
            )
        }
    }
}

@Composable
fun PerfilUsuario(
    userName: String,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Imagen de perfil
            contentDescription = "User profile image",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = userName,
                style = MaterialTheme.typography.bodyLarge
            )
            Row {
                IconButton(onClick = onEditProfile) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar perfil")
                }
                IconButton(onClick = onLogout) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
                }
            }
        }
    }
}

@Composable
fun PanelTareas(
    onViewTasks: () -> Unit,
    onCreateTask: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        ElevatedButton(onClick = onViewTasks) {
            Text("Tus tareas")
        }
        ElevatedButton(onClick = onCreateTask) {
            Text("Crear nueva tarea")
        }
    }
}

@Composable
fun RecentTaskSection(recentTask: String) {
    Text("Tarea más reciente:", style = MaterialTheme.typography.headlineMedium)
    Text(recentTask, style = MaterialTheme.typography.bodyMedium)
}

