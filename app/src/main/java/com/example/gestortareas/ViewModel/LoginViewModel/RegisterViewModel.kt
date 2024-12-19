package com.example.gestortareas.ViewModel.LoginViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestortareas.Model.Autenticacion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel(private val autenticacion: Autenticacion = Autenticacion()) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun registerUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _registerState.value = RegisterState.Error("Los campos no pueden estar vacíos")
            return
        }

        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            val result = autenticacion.registerUser(email, password)
            _registerState.value = if (result.isSuccess) {
                RegisterState.Success
            } else {
                RegisterState.Error("Error: ${result.exceptionOrNull()?.message}")
            }
        }
    }
}
