package com.example.gestortareas.View.LoginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    Column(
        //hace que  Column ocupe todo el espacio disponible del
        // contenedor padre, tanto en ancho como en alto.
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gestor de Tareas",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Log in ", fontSize = 30.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(30.dp))
        // Input para email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "mail", color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black) // Asegurar que el color del texto sea negro
        )
        Spacer(modifier = Modifier.height(20.dp))
// Input para contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "password", color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black) // Asegurar que el color del texto sea negro
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            //justificar a la derecha
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Forgot your password?", color = Color.Black)
            Row(
                //alinear verticalmente
                verticalAlignment = Alignment.CenterVertically
            ) {
                //estado checkbox
                var isChecked by remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    enabled = true //habilitar o no el check box
                )
                Text(text = "Recordarme", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { viewModel.loginUser(email, password) },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .padding(8.dp),
            //  Define la forma del botón, en este caso, con esquinas redondeadas.
            shape = RoundedCornerShape(12.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64B5F6),
                contentColor = Color.White,
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) { Text(text = "Inciar sesion", fontSize = 16.sp) }

        // Botón para ir a la pantalla de registro

        Button(
            onClick = onNavigateToRegister,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64B5F6),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
// Observamos el estado de inicio de sesión
        when (loginState) {
            is LoginState.Loading -> CircularProgressIndicator()
            is LoginState.Success -> {
                // Navegar a la HomeScreen
                LaunchedEffect(Unit) { onNavigateToHome() }
            }

            is LoginState.Error -> {
                Text(
                    text = (loginState as LoginState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            else -> Unit
        }
    }
}
