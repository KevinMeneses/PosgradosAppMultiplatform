package docentes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DocentesListViewModel(
    private val posgradoId: String,
    private val docenteRepository: DocenteRepository = DocenteRepository()
): ViewModel() {
    private val _state = MutableStateFlow(DocentesListState())
    val state = _state.asStateFlow()

    init {
        getDocentes()
    }

    private fun getDocentes() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    docentes = docenteRepository.getDocentesByPosgradoId(posgradoId),
                    isLoading = false
                )
            }
        }
    }
}