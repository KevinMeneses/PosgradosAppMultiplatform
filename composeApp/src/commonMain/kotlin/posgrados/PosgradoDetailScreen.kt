package posgrados

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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

object PosgradoDetailScreen {
    private const val SCREEN = "PosgradoDetailScreen"
    const val ARG = "posgrado"

    fun route(argument: String = "{$ARG}") =
        "$SCREEN/$argument"

    @Composable
    fun show(
        posgrado: Posgrado,
        onModulosClick: () -> Unit,
        onDocentesClick: () -> Unit,
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
                    text = posgrado.nombre,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = titleAlignment(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.15f),
                )
                Column(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(top = 25.dp, bottom = 20.dp)
                ) {
                    Text(
                        text = "Código SNIES: " + posgrado.codSnies,
                        color = Color.White
                    )
                    Text(
                        text = "Total créditos: " + posgrado.totalCreditos,
                        color = Color.White
                    )
                    Text(
                        text = "Duración: " + posgrado.duracion,
                        color = Color.White
                    )
                    Text(
                        text = "Valor semestre: " + posgrado.valorSemestre,
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(0.45f)
                        .padding(bottom = 20.dp)
                        .verticalScroll(rememberScrollState(0))
                ) {
                    Text(
                        text = "Descripción: " + posgrado.descripcion,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
                Column(
                    modifier = Modifier.weight(0.2f)
                ) {
                    Row(
                        modifier = Modifier
                            .clickable(onClick = onModulosClick)
                            .background(Color(1f, 1f, 1f, 0.05f))
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Módulos",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier
                            .clickable(onClick = onDocentesClick)
                            .background(Color(1f, 1f, 1f, 0.05f))
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Docentes",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}