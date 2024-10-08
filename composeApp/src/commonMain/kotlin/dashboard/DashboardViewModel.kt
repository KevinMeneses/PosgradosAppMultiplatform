package dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state
        .onStart { getDashboard() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DashboardState()
        )

    private var dashboard: Dashboard? = null

    fun processEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.SemesterSelected ->
                updateStateBySemester(event.semestre)
        }
    }

    private fun getDashboard() = viewModelScope.launch {
        dashboardRepository.getDashboard()
            .onSuccess { dashboard ->
                this@DashboardViewModel.dashboard = dashboard
                updateStateBySemester(dashboard.currentSemestre)
            }.onFailure {

            }
    }

    private fun updateStateBySemester(semester: Int) {
        val dashboard = dashboard ?: return
        _state.update { currentState ->
            val section = dashboard.section[semester]
            currentState.copy(
                posgrado = dashboard.posgrado,
                modulos = section?.modulos.orEmpty(),
                docentes = section?.docentes.orEmpty(),
                currentSemestre = dashboard.currentSemestre,
                selectedSemestre = semester,
                totalSemestres = dashboard.section.size,
                isLoading = false
            )
        }
    }
}