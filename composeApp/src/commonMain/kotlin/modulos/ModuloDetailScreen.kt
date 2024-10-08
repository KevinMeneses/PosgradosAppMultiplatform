package modulos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.BackButton
import common.BoxWithUniautonomaBackground
import common.titleAlignment

object ModuloDetailScreen {
    private const val SCREEN = "ModuloDetailScreen"
    const val ARG = "modulo"

    fun route(argument: String = "{$ARG}") =
        "$SCREEN/$argument"

    @Composable
    fun show(
        modulo: Modulo,
        onBackClick: () -> Unit
    ) {
        BoxWithUniautonomaBackground {
            BackButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp),
                onClick = onBackClick
            )
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = modulo.nombre,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = titleAlignment(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.13f)
                )
                Column(
                    modifier = Modifier
                        .weight(0.75f)
                        .padding(bottom = 20.dp)
                        .verticalScroll(rememberScrollState(0))
                ) {
                    Text(
                        text = "Descripción: " + modulo.descripcion,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
                Column(
                    modifier = Modifier.weight(0.12f)
                ) {
                    Text(
                        text = "Duración: " + modulo.duracion,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        text = "Créditos: " + modulo.creditos,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}