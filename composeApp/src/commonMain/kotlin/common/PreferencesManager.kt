package common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import authentication.Usuario
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

interface PreferencesManager {
    suspend fun saveAuthToken(authToken: String)
    suspend fun getAuthToken(): String
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String
    suspend fun saveUser(user: String)
    suspend fun getUser(): Usuario
}

class PreferencesManagerImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {
    override suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { it[AUTH_TOKEN] = authToken }
    }

    override suspend fun getAuthToken(): String =
        dataStore.data.map { it[AUTH_TOKEN].orEmpty() }.first()

    override suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.edit { it[REFRESH_TOKEN] = refreshToken }
    }

    override suspend fun getRefreshToken(): String =
        dataStore.data.map { it[REFRESH_TOKEN].orEmpty() }.first()

    override suspend fun saveUser(user: String) {
        dataStore.edit { it[USER] = user }
    }

    override suspend fun getUser(): Usuario {
        val usuarioJson = dataStore.data.map { it[USER].orEmpty() }.first()
        return Usuario.fromJson(usuarioJson)
    }

    private companion object {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER = stringPreferencesKey("user")
    }
}

expect fun getPreferencesManager(context: Any? = null): PreferencesManager?

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

internal const val dataStoreFileName = "dice.preferences_pb"