package loginLanding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.Background
import common.BoxWithUniautonomaBackground
import common.LoadingScreenWithShadow
import common.colorGold
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import posgradosappmultiplatform.composeapp.generated.resources.Res
import posgradosappmultiplatform.composeapp.generated.resources.casita
import posgradosappmultiplatform.composeapp.generated.resources.especializaciones
import posgradosappmultiplatform.composeapp.generated.resources.facebook
import posgradosappmultiplatform.composeapp.generated.resources.ic_uniautonoma
import posgradosappmultiplatform.composeapp.generated.resources.instagram
import posgradosappmultiplatform.composeapp.generated.resources.logo_escuela
import posgradosappmultiplatform.composeapp.generated.resources.pqrs
import posgradosappmultiplatform.composeapp.generated.resources.twitter
import posgradosappmultiplatform.composeapp.generated.resources.ubicacion

object LoginLandingScreen {
    @Composable
    fun show(
        state: LoginLandingState,
        onNavigate: (LoginLandingNavigation) -> Unit,
        onEvent: (LoginLandingEvent) -> Unit
    ) {
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(state.isLoggedIn) {
            if (state.isLoggedIn) {
                onNavigate(LoginLandingNavigation.DASHBOARD)
            }
        }

        if (state.loginError) coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = "Algo salió mal, por favor intentalo de nuevo en un momento"
            )
        }

        BoxWithUniautonomaBackground(Background.LANDING) {
            if (state.isLoading) LoadingScreenWithShadow()
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.33f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_uniautonoma),
                        contentDescription = ""
                    )
                    Text(
                        text = "www.uniautonoma.edu.co",
                        modifier = Modifier.clickable { },
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.35f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.size(60.dp))
                    Text(
                        text = "¿Eres estudiante de posgrados?",
                        color = Color.White
                    )
                    Spacer(Modifier.size(10.dp))
                    Button(
                        onClick = {
                            onEvent(LoginLandingEvent.SSOLogin)
                        },
                        content = {
                            Text(
                                text = "INICIA SESIÓN",
                                color = Color.White
                            )
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = colorGold
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.15f)
                ) {
                    Spacer(Modifier.weight(0.08f))
                    Column(
                        modifier = Modifier
                            .weight(0.92f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier.clickable {
                                onNavigate(LoginLandingNavigation.ESCUELA)
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.casita),
                                modifier = Modifier.padding(end = 5.dp).size(25.dp),
                                contentDescription = ""
                            )
                            Text(
                                text = "LA ESCUELA",
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.size(1.dp))
                        Row(
                            modifier = Modifier.clickable {
                                onNavigate(LoginLandingNavigation.ESPECIALIZACIONES)
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.especializaciones),
                                modifier = Modifier.padding(end = 5.dp).size(25.dp),
                                contentDescription = ""
                            )
                            Text(
                                text = "ESPECIALIZACIONES",
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.size(1.dp))
                        Row(
                            modifier = Modifier.clickable {
                                onNavigate(LoginLandingNavigation.MAPA)
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.ubicacion),
                                modifier = Modifier.padding(end = 5.dp).size(25.dp),
                                contentDescription = ""
                            )
                            Text(
                                text = "DONDE NOS UBICAMOS",
                                color = Color.White
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.17f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.facebook),
                            modifier = Modifier
                                .padding(start = 15.dp, end = 5.dp)
                                .size(25.dp)
                                .clickable { },
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(Res.drawable.twitter),
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(25.dp)
                                .clickable { },
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(Res.drawable.instagram),
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(25.dp)
                                .clickable { },
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(Res.drawable.pqrs),
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(50.dp)
                                .clickable {
                                    onNavigate(LoginLandingNavigation.PQRS)
                                },
                            contentDescription = ""
                        )
                    }
                    Image(
                        painter = painterResource(Res.drawable.logo_escuela),
                        modifier = Modifier.padding(end = 5.dp).size(155.dp),
                        contentDescription = ""
                    )
                }
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}