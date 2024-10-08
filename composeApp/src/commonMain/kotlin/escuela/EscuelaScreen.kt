package escuela

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.BackButton
import common.Background
import common.BoxWithUniautonomaBackground
import common.LoadingScreen
import common.colorGold
import org.jetbrains.compose.resources.painterResource
import posgradosappmultiplatform.composeapp.generated.resources.Res
import posgradosappmultiplatform.composeapp.generated.resources.logo_escuela

object EscuelaScreen {
    @Composable
    fun show(
        state: EscuelaState,
        onBackClick: () -> Unit
    ) {
        BoxWithUniautonomaBackground(
            background = Background.LOGO
        ) {
            if (state.isLoading) LoadingScreen()
            BackButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp),
                onClick = onBackClick
            )
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(Res.drawable.logo_escuela),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 20.dp),
                    contentDescription = "",
                    alignment = Alignment.TopCenter
                )
                Column(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 40.dp)
                ) {
                    Text(
                        text = "La Escuela",
                        color = colorGold,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(0.04f)
                    )
                    Text(
                        text = "¿QUIENES SOMOS?",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(0.04f)
                    )

                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .weight(0.70f)
                            .verticalScroll(rememberScrollState(0))
                    ) {
                        Text(
                            text = state.escuela?.descripcion.orEmpty(),
                            textAlign = TextAlign.Justify,
                            color = Color.White
                        )
                    }

                    Text(
                        text = state.escuela?.director.orEmpty(),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .weight(0.2f),
                        color = Color.White
                    )
                }

            }
        }
    }
}