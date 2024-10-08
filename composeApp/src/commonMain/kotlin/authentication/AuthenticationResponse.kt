package authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val token: String,
    val refreshToken: String
)
