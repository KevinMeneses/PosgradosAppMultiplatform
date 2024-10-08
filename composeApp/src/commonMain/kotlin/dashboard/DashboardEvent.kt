package dashboard

import modulos.Semestre

sealed interface DashboardEvent {
    data class SemesterSelected(val semestre: Semestre) : DashboardEvent
}