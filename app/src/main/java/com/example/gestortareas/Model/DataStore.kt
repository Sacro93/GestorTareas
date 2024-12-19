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
        preferences.get(UserPreferencesKeys.IS_LOGGED_IN) ?: false
    }
}
fun getLoggedInUserEmail(context: Context): Flow<String?> {
    return context.dataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.USER_EMAIL]
    }
}
