package docentes

import kotlinx.serialization.Serializable

@Serializable
data class Docente(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val profesion: String,
    val descripcion: String,
    val imagen: String
)