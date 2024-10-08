package modulos

import kotlinx.serialization.Serializable

@Serializable
data class Modulo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val creditos: Int,
    val duracion: String,
    val semestre: Int
)