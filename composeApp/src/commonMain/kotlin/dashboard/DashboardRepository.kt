package dashboard

import common.BASE_URL
import common.PreferencesManager
import di.getHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.coroutines.CancellationException

class DashboardRepository(
    private val httpClient: HttpClient = getHttpClient(),
    private val preferencesManager: PreferencesManager?
) {
    suspend fun getDashboard(
        semester: Int? = null
    ): Result<Dashboard> = try {
        val authToken = preferencesManager?.getAuthToken()
        val user = preferencesManager?.getUser()!!
        val dashboard = httpClient.get("$BASE_URL/dashboard") {
            header("Authorization", "bearer $authToken")
            parameter("id_usuario", user.codigo)
            parameter("id_posgrado", user.idPosgrado)
            parameter("semestre", semester ?: user.semestre)
        }.body<Dashboard>()

        Result.success(dashboard)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}