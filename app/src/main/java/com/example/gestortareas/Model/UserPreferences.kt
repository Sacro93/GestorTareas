package com.example.gestortareas.Model
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_prefs")

object UserPreferencesKeys {
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val USER_EMAIL = stringPreferencesKey("user_email")
}

suspend fun saveUserLoginState(context: Context, isLoggedIn: Boolean, email: String?) {
    context.dataStore.edit { preferences ->
        preferences[UserPreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        email?.let { preferences[UserPreferencesKeys.USER_EMAIL] = it }
    }
}

fun getUserLoginState(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.IS_LOGGED_IN] ?: false // Add ?: false to handle missing key
    }
}

fun getLoggedInUserEmail(context: Context): Flow<String?> {
    return context.dataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.USER_EMAIL]
    }
}

/*
* Explanation of Changes:

import android.content.Context:
* This import is necessary because the context parameter is of type Context in com.example.gestortareas.Model.getUserLoginStatertareas.Model.getUserLoginState.
?: false in com.example.gestortareas.Model.getUserLoginStatertareas.Model.getUserLoginState:
* This is an Elvis operator that checks if the value of preferences
* [com.example.gestortareas.Model.UserPreferencesKeysareas.Model.UserPreferencesKeys.IS_LOGGED_IN] is not null. If it's null, i
* t returns false. This is important to handle cases where the key might not exist yet in the DataStore.*/