package com.example.gestortareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gestortareas.ViewModel.Navigation.NavigationWrapper
import com.example.gestortareas.ui.theme.GestorTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorTareasTheme {
               //puerta de entrada
                NavigationWrapper()
            }
        }
    }
}


