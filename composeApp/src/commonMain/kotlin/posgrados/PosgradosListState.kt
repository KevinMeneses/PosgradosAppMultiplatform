package posgrados

data class PosgradosListState(
    val posgrados: List<Posgrado> = listOf(),
    val isLoading: Boolean = true
)