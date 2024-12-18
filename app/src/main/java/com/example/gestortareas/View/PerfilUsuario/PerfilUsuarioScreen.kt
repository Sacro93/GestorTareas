package com.example.gestortareas.View.PerfilUsuario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gestortareas.ViewModel.HomeScreenViewModel

//edicion del perfil completa

//resta ver como conectar con BD por imagen cargada y conectar con model 18-12-2024
@Composable
fun PerfilUsuarioScreen(

    onSeeProfile:()-> Unit,
    viewModel: HomeScreenViewModel,
    onSaveChanges: (String) -> Unit) {
    val userName by viewModel.userName.collectAsState()
    val profileImageUrl by viewModel.profileImageUrl.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_report_image)
        )
        OutlinedTextField(
            value = userName,
            onValueChange = { newName -> onSaveChanges(newName) },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { /* Save Changes */ }) {
            Text("Guardar Cambios")
        }
    }
}

/*
* @Composable
fun PerfilUsuarioHomeScreen(
    userName: String,
    profileImageUrl: String?,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            placeholder = ColorPainter(MaterialTheme.colorScheme.primary),
            error = ColorPainter(MaterialTheme.colorScheme.error)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Bienvenido a la aplicación",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box {
            IconButton(onClick = { isDropdownExpanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Opciones de perfil"
                )
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Editar Perfil") },
                    onClick = {
                        isDropdownExpanded = false
                        onEditProfile()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Cerrar Sesión") },
                    onClick = {
                        isDropdownExpanded = false
                        onLogout()
                    }
                )
            }
        }
    }
}
*/
