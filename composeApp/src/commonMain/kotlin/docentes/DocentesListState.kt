package docentes

data class DocentesListState(
    val docentes: List<Docente> = listOf(),
    val isLoading: Boolean = true
)
