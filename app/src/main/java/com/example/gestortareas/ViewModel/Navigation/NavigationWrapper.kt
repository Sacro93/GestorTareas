package com.example.gestortareas.ViewModel.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestortareas.View.HomeScreen.HomeScreen
import com.example.gestortareas.View.LoginScreen.LoginScreen
import com.example.gestortareas.View.LoginScreen.LoginViewModel
import com.example.gestortareas.View.RegisterScreen.RegisterScreen
import com.example.gestortareas.ViewModel.HomeScreenViewModel.HomeScreenViewModel
import com.example.gestortareas.ViewModel.LoginViewModel.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        // Pantalla de Login
        composable(route = Routes.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                viewModel = loginViewModel
            )
        }

        // Pantalla de Registro
        composable(route = Routes.Register.route) {
            val registerViewModel: RegisterViewModel = viewModel()

            RegisterScreen(
                onNavigateToLogin = { navController.navigate(Routes.Login.route) },
                viewModel = registerViewModel
            )
        }
        // Pantalla Principal (Home)
        composable(route = Routes.Home.route) {
            val homeViewModel: HomeScreenViewModel = viewModel()
            HomeScreen(
                onSeeProfile = { navController.navigate(Routes.Profile.route) },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
                onViewTasks = { navController.navigate(Routes.ViewTasks.route) },
                onCreateTask = { navController.navigate(Routes.CreateTask.route) },
                viewModel = homeViewModel
            )
        }
    }
}
