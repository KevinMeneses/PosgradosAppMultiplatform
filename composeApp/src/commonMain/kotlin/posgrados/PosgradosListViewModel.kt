package posgrados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PosgradosListViewModel(
    private val posgradoRepository: PosgradoRepository = PosgradoRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(PosgradosListState())
    val state = _state.asStateFlow()

    init {
        getAllPosgrados()
    }

    private fun getAllPosgrados() = viewModelScope.launch {
        _state.update {
            it.copy(
                posgrados = posgradoRepository.getAllPosgrados(),
                isLoading = false
            )
        }
    }
}