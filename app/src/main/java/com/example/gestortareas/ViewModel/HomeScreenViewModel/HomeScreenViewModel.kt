package com.example.gestortareas.ViewModel.HomeScreenViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val context: Context) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            try {
                isLoggedInFlow(context).collect { state ->
                    _isLoggedIn.value = state
                }
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error al obtener el estado de inicio de sesión", e)
                // Manejar el error, por ejemplo, mostrar un mensaje al usuario
            }
        }
    }
}

// En una clase de repositorio o fuente de datos (ejemplo)
fun isLoggedInFlow(context: Context): Flow<Boolean> {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)
    return flow { emit(isLoggedIn) }
}