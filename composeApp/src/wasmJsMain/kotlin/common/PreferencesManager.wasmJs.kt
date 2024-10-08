package common

import authentication.Usuario

class PreferencesManagerWebImpl: PreferencesManager {
    override suspend fun saveAuthToken(authToken: String) {

    }

    override suspend fun getAuthToken(): String {
        return ""
    }

    override suspend fun saveRefreshToken(refreshToken: String) {

    }

    override suspend fun getRefreshToken(): String {
        return ""
    }

    override suspend fun saveUser(user: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): Usuario {
        TODO("Not yet implemented")
    }
}

actual fun getPreferencesManager(context: Any?): PreferencesManager? {
    return PreferencesManagerWebImpl()
}