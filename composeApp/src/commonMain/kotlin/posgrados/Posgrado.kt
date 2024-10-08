package posgrados

import kotlinx.serialization.Serializable

@Serializable
data class Posgrado(
    val id: Int,
    val codSnies: String,
    val nombre: String,
    val duracion: String,
    val totalCreditos: Int,
    val descripcion: String,
    val valorSemestre: String
)