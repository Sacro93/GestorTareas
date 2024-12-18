package com.example.gestortareas.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.concurrent.Task

class HomeScreenViewModel : ViewModel() {

    ////var isTaskLoaded by mutableStateOf(false)

    // Estado para el perfil del usuario
    private val _userName = MutableStateFlow("John Doe")
    val userName: StateFlow<String> = _userName

    private val _profileImageUrl = MutableStateFlow<String?>(null)
    val profileImageUrl: StateFlow<String?> = _profileImageUrl

    // Estado para la tarea más reciente
    private val _recentTask = MutableStateFlow<Task?>(null)
    val recentTask: StateFlow<Task?> = _recentTask

    // Simulación de carga de datos
    init {
        fetchUserData()
        fetchRecentTask()
    }

    private fun fetchUserData() {
        // Aquí iría la lógica para cargar los datos del usuario desde una API o base de datos
        _userName.value = "Jane Doe"
        _profileImageUrl.value = "https://example.com/profile.jpg"
    }

    private fun fetchRecentTask() {
        viewModelScope.launch {
            // Simula una tarea reciente o cárgala desde un repositorio
            _recentTask.value = Task(
                name = "Comprar leche",
                description = "Ir al supermercado",
                dateTime = "2024-12-18 10:00",
                imageUrl = "https://example.com/image.jpg"
            )
        }
    }

    data class Task(
        val name: String,
        val description: String,
        val dateTime: String,
        val imageUrl: String? = null
    )

}