package com.example.gestortareas.ViewModel.Navigation


//Definir las rutas de navegación en un objeto
// separado para mantener las constantes organizadas.

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object LogOut: Routes("logout")
    object Register : Routes("register")
    object Home : Routes("home")
    object ViewTasks : Routes("view_tasks")
    object CreateTask : Routes("create_task")
    object Profile : Routes("profile")
}
