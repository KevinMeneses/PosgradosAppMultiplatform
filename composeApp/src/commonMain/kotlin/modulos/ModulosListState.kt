package modulos

typealias Semestre = Int

data class ModulosListState(
    val sections: Map<Semestre, List<Modulo>> = mapOf(),
    val isLoading: Boolean = true
)
