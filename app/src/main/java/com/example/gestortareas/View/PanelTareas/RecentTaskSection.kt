package com.example.gestortareas.View.PanelTareas

//En este codigo estara el View que se mostrara en la Home Screen que indicara actividades recientes


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.SemanticsProperties.Heading
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun RecentTaskSection(
    title: String,
    description: String,
    dateTime: String,
    imageUrl: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }, // Make the card clickable
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() }            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.semantics { contentDescription = description }            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = dateTime,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.semantics { contentDescription = "Date and time: $dateTime" }            )

            if (!imageUrl.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Task image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    placeholder = ColorPainter(MaterialTheme.colorScheme.primary),
                    error = @androidx.compose.runtime.Composable {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Error loading image")
                    } as Painter?
                )
            }
        }
    }
}


/*
@Composable
fun RecentTaskSection(
    title: String,
    description: String,
    dateTime: String,
    imageUrl: String? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = dateTime,
                style = MaterialTheme.typography.labelSmall
            )
            if (!imageUrl.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                // Es el nuevo componente recomendado por Coil para cargar imágenes en Compose.
                AsyncImage(
                    //El atributo model se usa para especificar la URL de la imagen.
                    model = imageUrl,
                    contentDescription = "Task image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }
    }
}
*/