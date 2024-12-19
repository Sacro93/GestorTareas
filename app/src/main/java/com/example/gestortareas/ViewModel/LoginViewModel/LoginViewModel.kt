package com.example.gestortareas.ViewModel.LoginViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestortareas.Model.Autenticacion
import com.example.gestortareas.View.LoginScreen.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val autenticacion: Autenticacion = Autenticacion()) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginState.value = LoginState.Error("Los campos no pueden estar vacíos")
            return
        }

        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            val result = autenticacion.loginUser(email, password)
            _loginState.value = if (result.isSuccess) {
                LoginState.Success
            } else {
                LoginState.Error("Error: ${result.exceptionOrNull()?.message}")
            }
        }
    }
}
