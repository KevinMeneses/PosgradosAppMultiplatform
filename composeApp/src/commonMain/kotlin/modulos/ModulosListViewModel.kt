package modulos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ModulosListViewModel(
    private val posgradoId: String,
    private val moduloRepository: ModuloRepository = ModuloRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(ModulosListState())
    val state = _state.asStateFlow()

    init {
        getModulos()
    }

    private fun getModulos() = viewModelScope.launch {
        val modulos = moduloRepository.getModulosByPosgradoId(posgradoId)
        val sections = modulos
            .map { it.semestre }
            .distinct()
            .associateWith { semestre ->
                modulos.filter {
                    it.semestre == semestre
                }
            }

        _state.update {
            it.copy(
                sections = sections,
                isLoading = false
            )
        }
    }
}