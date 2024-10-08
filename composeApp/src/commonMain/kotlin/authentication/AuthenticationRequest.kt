package authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(val correo: String)
