package com.example.gestortareas.View.PerfilUsuario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gestortareas.ViewModel.HomeScreenViewModel


//falta ver como conectar con BD para cargar imagen correctamente y que se actualice correctamente nombre => sera actualizacion que se hara en PerfilUsuarioScreen ?
@Composable
fun PerfilUsuarioHomeScreen(viewModel: HomeScreenViewModel, onEditProfile: () -> Unit, onLogout: () -> Unit) {
    val userName by viewModel.userName.collectAsState()
    val profileImageUrl by viewModel.profileImageUrl.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = "Imagen Perfil",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_report_image)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Bienvenido a la aplicación",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = { onEditProfile() }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Editar perfil")
        }
    }
}
