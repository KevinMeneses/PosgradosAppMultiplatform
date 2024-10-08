package docentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
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

object DocenteDetailScreen {
    private const val SCREEN = "DocenteDetailScreen"
    const val ARG = "docente"

    fun route(argument: String = "{$ARG}") =
        "$SCREEN/$argument"

    @Composable
    fun show(
        docente: Docente,
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
                modifier = Modifier.padding(25.dp)
            ) {
                /*AsyncImage(
                    model = docente.imagen,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(50.dp)
                )*/
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .height(150.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = docente.nombre + " " + docente.apellido,
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = docente.profesion,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState(0))
                ) {
                    Text(
                        text = docente.descripcion,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}