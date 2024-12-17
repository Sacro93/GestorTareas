package com.example.gestortareas.ViewModel.RegisterViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestortareas.Model.Autenticacion
import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

data class RegisterFormState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
    fun isFormValid(): Boolean = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
}

class RegisterViewModel(
    private val authRepository: Autenticacion = Autenticacion()
) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    var formState by mutableStateOf(RegisterFormState())
        private set

    fun onEmailChanged(newEmail: String) {
        formState = formState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        formState = formState.copy(password = newPassword)
    }

    fun onConfirmPasswordChanged(newConfirmPassword: String) {
        formState = formState.copy(confirmPassword = newConfirmPassword)
    }

    fun registerUser(email: String, password: String, confirmPassword: String){
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            _registerState.value = RegisterState.Error("Los campos no pueden estar vacíos")
            return
        }
        if (password != confirmPassword) {
            _registerState.value = RegisterState.Error("Las contraseñas no coinciden")
            return
        }
        if (!isValidPassword(password)) {
            _registerState.value = RegisterState.Error("La contraseña debe tener al menos 8 caracteres, una letra y un número.")
            return
        }

        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _registerState.value = RegisterState.Success
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Error al registrar usuario: ${e.message}")
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")
        return regex.matches(password)
    }
}
