package loginLanding

import dashboard.DashboardScreen
import escuela.EscuelaScreen
import map.MapScreen
import posgrados.PosgradosListScreen
import pqrs.PqrsScreen

enum class LoginLandingNavigation(val destination: String) {
    DASHBOARD(DashboardScreen::class.toString()),
    ESCUELA(EscuelaScreen::class.toString()),
    ESPECIALIZACIONES(PosgradosListScreen::class.toString()),
    MAPA(MapScreen::class.toString()),
    PQRS(PqrsScreen::class.toString())
}