package escuela

import common.BASE_URL
import di.getHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EscuelaRepository(
    private val httpClient: HttpClient = getHttpClient()
) {
    suspend fun getEscuela() = try {
        httpClient.get("$BASE_URL/escuela/1").body()
    } catch (e: Exception) {
        Escuela(
            id = 1,
            director = "Pedro Pablo",
            descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            correo = "pedro@pablo.com",
            direccion = "Calle 123 #45-67",
            latitud = 123.0,
            longitud = 456.0
        )
    }
}