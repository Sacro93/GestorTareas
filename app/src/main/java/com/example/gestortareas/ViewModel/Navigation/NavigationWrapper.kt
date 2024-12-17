package com.example.gestortareas.ViewModel.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestortareas.View.HomeScreen.HomeScreen
import com.example.gestortareas.View.LoginScreen.LoginScreen
import com.example.gestortareas.View.RegisterScreen.RegisterScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ObjectRutas.LOGIN) {
        composable(route = ObjectRutas.LOGIN) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(ObjectRutas.REGISTER) },
                onNavigateToHome = { navController.navigate(ObjectRutas.HOME) }
            )
        }

        composable(route = ObjectRutas.REGISTER) {
            RegisterScreen(navController = navController)
        }

        composable(route = ObjectRutas.HOME) {
            HomeScreen(
                onEditProfile = { /* Navegar o mostrar opciones de edición */ },
                onLogout = { navController.navigate(ObjectRutas.LOGIN) },
                onViewTasks = { /* Implementar navegación a "Ver Tareas" */ },
                onCreateTask = { /* Implementar navegación a "Crear Nueva Tarea" */ }
            )
        }
    }
}
