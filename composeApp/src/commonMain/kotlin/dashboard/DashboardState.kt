package dashboard

import docentes.Docente
import modulos.Modulo
import modulos.Semestre
import posgrados.Posgrado

data class DashboardState(
    val posgrado: Posgrado? = null,
    val modulos: List<Modulo> = emptyList(),
    val docentes: List<Docente> = emptyList(),
    val currentSemestre: Semestre = 1,
    val selectedSemestre: Semestre = 1,
    val totalSemestres: Semestre = 2,
    val isLoading: Boolean = true
)
