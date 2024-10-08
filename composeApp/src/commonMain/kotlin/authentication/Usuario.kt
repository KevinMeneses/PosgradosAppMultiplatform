package authentication

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Usuario(
    val codigo: String,
    val nombre: String,
    val apellido: String,
    val correo: String?,
    val idPosgrado: Int,
    val semestre: Int
) {
    companion object {
        fun fromJson(json: String) = Json.decodeFromString<Usuario>(json)
    }
}