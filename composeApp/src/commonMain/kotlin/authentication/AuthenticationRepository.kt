package authentication

import common.BASE_URL
import common.PreferencesManager
import di.getHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationRepository(
    private val httpClient: HttpClient = getHttpClient(),
    private val preferencesManager: PreferencesManager?,
    private val jwtParser: JwtParser = getJwtParser()
) {
    suspend fun loginWithEmailSSO(email: String): Result<Unit> = try {
        val authRequest = AuthenticationRequest(email)
        val authResponse = httpClient.post {
            url("$BASE_URL/auth/sso")
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body<AuthenticationResponse>()

        val user = jwtParser.getClaim(authResponse.refreshToken, "usuario")
        preferencesManager?.saveUser(user!!)
        preferencesManager?.saveAuthToken(authResponse.token)
        preferencesManager?.saveRefreshToken(authResponse.refreshToken)

        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}