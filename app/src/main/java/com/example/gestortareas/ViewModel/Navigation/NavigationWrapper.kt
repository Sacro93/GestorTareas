package com.example.gestortareas.ViewModel.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestortareas.View.HomeScreen.HomeScreen
import com.example.gestortareas.View.LoginScreen.LoginScreen
import com.example.gestortareas.View.PanelTareas.PanelTareas
import com.example.gestortareas.View.PerfilUsuario.PerfilUsuarioScreen
import com.example.gestortareas.View.RegisterScreen.RegisterScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                onNavigateToHome = { navController.navigate(Routes.Home.route) }
            )
        }

        composable(route = Routes.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Routes.Home.route) {
            HomeScreen(
                /*aque debere poder ver  : tocar perfil y ver perfil
                * tocar salir y poder logout
                * tocar lista tareas, navegar lista tareas
                * tocar crear tarea, navegar crear tarea
                * */

                onSeeProfile = { navController.navigate(Routes.Profile.route) },
                onLogout = { navController.navigate(Routes.LogOut.route) },
                onViewTasks = { navController.navigate(Routes.ViewTasks.route) },
                onCreateTask = { navController.navigate(Routes.CreateTask.route) },
                viewModel = TODO()
            )
        }

    }
}