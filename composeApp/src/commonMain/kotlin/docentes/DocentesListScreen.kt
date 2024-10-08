package docentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.BackButton
import common.BoxWithUniautonomaBackground
import common.LoadingScreen

object DocentesListScreen {
    private const val SCREEN = "DocentesListScreen"
    const val ARG = "posgradoId"

    fun route(argument: String = "{$ARG}") =
        "$SCREEN/$argument"

    @Composable
    fun show(
        state: DocentesListState,
        onDocenteClick: (Docente) -> Unit,
        onBackClick: () -> Unit
    ) {
        BoxWithUniautonomaBackground {
            if (state.isLoading) LoadingScreen()
            BackButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp),
                onClick = onBackClick
            )
            Column {
                Row(
                    modifier = Modifier
                        .weight(0.15f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "DOCENTES",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .weight(0.85f)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                ) {
                    items(state.docentes) {
                        Card(
                            modifier = Modifier
                                .clickable { onDocenteClick(it) }
                                .padding(bottom = 15.dp)
                                .fillMaxWidth(),
                            elevation = 5.dp
                        ) {
                            Row {
                                /*AsyncImage(
                                    model = it.imagen,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .size(50.dp)
                                )*/
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .size(50.dp)
                                )
                                Column {
                                    Text(
                                        text = it.nombre + " " + it.apellido,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                    Text(
                                        text = it.profesion,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                                        minLines = 2,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}