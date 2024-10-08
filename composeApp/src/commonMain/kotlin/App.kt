import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import authentication.AuthenticationRepository
import common.defAnimComposable
import common.getPlatFormContext
import common.getPreferencesManager
import common.stringArgs
import dashboard.DashboardRepository
import dashboard.DashboardScreen
import dashboard.DashboardViewModel
import docentes.Docente
import docentes.DocenteDetailScreen
import docentes.DocentesListScreen
import docentes.DocentesListViewModel
import escuela.EscuelaScreen
import escuela.EscuelaViewModel
import extensions.collectAsStateMultiplatform
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import loginLanding.LoginLandingScreen
import loginLanding.LoginLandingViewModel
import map.MapScreen
import modulos.Modulo
import modulos.ModuloDetailScreen
import modulos.ModulosListScreen
import modulos.ModulosListViewModel
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import org.jetbrains.compose.ui.tooling.preview.Preview
import posgrados.Posgrado
import posgrados.PosgradoDetailScreen
import posgrados.PosgradosListScreen
import posgrados.PosgradosListViewModel
import pqrs.PqrsScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val context = getPlatFormContext()
        val preferencesManager = getPreferencesManager(context)
        val onBackClick = remember {
            fun () {
                navController.popBackStack()
            }
        }

        NavHost(
            navController = navController,
            startDestination = LoginLandingScreen::class.toString()
        ) {
            composable(
                route = LoginLandingScreen::class.toString(),
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                content = {
                    val loginLandingViewModel = viewModel {
                        LoginLandingViewModel(
                            authenticationRepository = AuthenticationRepository(
                                preferencesManager = preferencesManager
                            )
                        )
                    }
                    LoginLandingScreen.show(
                        state = loginLandingViewModel.state.collectAsStateMultiplatform().value,
                        onNavigate = { navController.navigate(it.destination) },
                        onEvent = loginLandingViewModel::processEvent
                    )
                }
            )

            defAnimComposable(
                route = DashboardScreen::class.toString(),
                content = {
                    val dashboardViewModel = viewModel {
                        DashboardViewModel(
                            dashboardRepository = DashboardRepository(
                                preferencesManager = preferencesManager
                            )
                        )
                    }
                    DashboardScreen.show(
                        state = dashboardViewModel.state.collectAsStateMultiplatform().value,
                        onModuloClick = { modulo ->
                            val moduloJson = Json.encodeToString(modulo)
                            val route = ModuloDetailScreen.route(moduloJson)
                            navController.navigate(route)
                        },
                        onDocenteClick = { docente ->
                            val docenteJson = Json.encodeToString(docente)
                            val encodedJson = UrlEncoderUtil.encode(docenteJson)
                            val route = DocenteDetailScreen.route(encodedJson)
                            navController.navigate(route)
                        },
                        onEvent = dashboardViewModel::processEvent,
                        onBackClick = onBackClick
                    )
                }
            )

            defAnimComposable(route = EscuelaScreen::class.toString()) {
                val escuelaViewModel = viewModel { EscuelaViewModel() }
                EscuelaScreen.show(
                    state = escuelaViewModel.state.collectAsStateMultiplatform().value,
                    onBackClick = onBackClick
                )
            }

            defAnimComposable(
                route = PosgradosListScreen::class.toString(),
                content = {
                    val posgradosListViewModel = viewModel { PosgradosListViewModel() }
                    PosgradosListScreen.show(
                        state = posgradosListViewModel.state.collectAsStateMultiplatform().value,
                        onPosgradoClicked = {
                            val posgradoJson = Json.encodeToString(it)
                            navController.navigate(PosgradoDetailScreen.route(posgradoJson))
                        },
                        onBackClick = onBackClick
                    )
                }
            )

            defAnimComposable(
                route = PosgradoDetailScreen.route(),
                arguments = stringArgs(PosgradoDetailScreen.ARG),
                content = {
                    val posgradoJson = it.arguments?.getString(PosgradoDetailScreen.ARG)
                    val posgrado = Json.decodeFromString<Posgrado>(posgradoJson!!)
                    val posgradoId = posgrado.id.toString()

                    PosgradoDetailScreen.show(
                        posgrado = posgrado,
                        onModulosClick = {
                            val route = ModulosListScreen.route(posgradoId)
                            navController.navigate(route)
                        },
                        onDocentesClick = {
                            val route = DocentesListScreen.route(posgradoId)
                            navController.navigate(route)
                        },
                        onBackClick = onBackClick
                    )
                }
            )

            defAnimComposable(
                route = ModulosListScreen.route(),
                arguments = stringArgs(ModulosListScreen.ARG),
                content = {
                    val posgradoId = it.arguments?.getString(ModulosListScreen.ARG)!!
                    val modulosListViewModel = viewModel { ModulosListViewModel(posgradoId) }
                    ModulosListScreen.show(
                        state = modulosListViewModel.state.collectAsStateMultiplatform().value,
                        onModuloClick = { modulo ->
                            val moduloJson = Json.encodeToString(modulo)
                            val route = ModuloDetailScreen.route(moduloJson)
                            navController.navigate(route)
                        },
                        onBackClick = onBackClick
                    )
                }
            )

            defAnimComposable(
                route = ModuloDetailScreen.route(),
                arguments = stringArgs(ModuloDetailScreen.ARG),
                content = {
                    val moduloJson = it.arguments?.getString(ModuloDetailScreen.ARG)
                    val modulo = Json.decodeFromString<Modulo>(moduloJson!!)
                    ModuloDetailScreen.show(modulo, onBackClick)
                }
            )

            defAnimComposable(
                route = DocentesListScreen.route(),
                arguments = stringArgs(DocentesListScreen.ARG),
                content = {
                    val posgradoId = it.arguments?.getString(DocentesListScreen.ARG)!!
                    val docentesListViewModel = viewModel { DocentesListViewModel(posgradoId) }
                    DocentesListScreen.show(
                        state = docentesListViewModel.state.collectAsStateMultiplatform().value,
                        onDocenteClick = { docente ->
                            val docenteJson = Json.encodeToString(docente)
                            val encodedJson = UrlEncoderUtil.encode(docenteJson)
                            val route = DocenteDetailScreen.route(encodedJson)
                            navController.navigate(route)
                        },
                        onBackClick = onBackClick
                    )
                }
            )

            defAnimComposable(
                route = DocenteDetailScreen.route(),
                arguments = stringArgs(DocenteDetailScreen.ARG),
                content = {
                    val docenteJson = it.arguments?.getString(DocenteDetailScreen.ARG)
                    val docente = Json.decodeFromString<Docente>(docenteJson!!)
                    DocenteDetailScreen.show(docente, onBackClick)
                }
            )

            defAnimComposable(route = MapScreen::class.toString()) {
                MapScreen.show()
            }

            defAnimComposable(route = PqrsScreen::class.toString()) {
                PqrsScreen.show(onBackClick)
            }
        }
    }
}