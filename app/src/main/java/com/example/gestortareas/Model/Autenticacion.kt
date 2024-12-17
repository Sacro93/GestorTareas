package com.example.gestortareas.Model

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

//Utilizamos FirebaseAuth.getInstance() para interactuar con Firebase Authentication
class Autenticacion(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()) {

    // Función para registrar un usuario
    suspend fun registerUser(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit) // Registro exitoso
        } catch (e: Exception) {
            Result.failure(e) // Error durante el registro
        }
    }
}