package escuela

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EscuelaViewModel(
    private val escuelaRepository: EscuelaRepository = EscuelaRepository()
): ViewModel() {

    private val _state = MutableStateFlow(EscuelaState())
    val state = _state.asStateFlow()

    init {
        getEscuela()
    }

    private fun getEscuela() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    escuela = escuelaRepository.getEscuela(),
                    isLoading = false
                )
            }
        }
    }
}