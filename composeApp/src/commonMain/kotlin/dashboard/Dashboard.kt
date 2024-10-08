package dashboard

import docentes.Docente
import kotlinx.serialization.Serializable
import modulos.Modulo
import posgrados.Posgrado

@Serializable
data class Dashboard(
    val posgrado: Posgrado,
    val section: Map<Int, Section>,
    val currentSemestre: Int
) {
    @Serializable
    data class Section(
        val modulos: List<Modulo>,
        val docentes: List<Docente>,
        //val horarios: List<Horario>,
        //val calificaciones: List<Calificacion>,
    )
}