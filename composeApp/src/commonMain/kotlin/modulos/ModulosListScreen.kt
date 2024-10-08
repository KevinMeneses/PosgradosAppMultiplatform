package modulos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
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

object ModulosListScreen {
    private const val SCREEN = "ModulosListScreen"
    const val ARG = "posgradoId"

    fun route(argument: String = "{$ARG}") =
        "$SCREEN/$argument"

    @Composable
    fun show(
        state: ModulosListState,
        onModuloClick: (Modulo) -> Unit,
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
                        text = "MODULOS",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .weight(0.85f)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                ) {
                    for (section in state.sections) {
                        item {
                            Text(
                                text = "Semestre " + section.key,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 15.dp),
                                color = Color.White
                            )
                        }
                        items(section.value) {
                            Card(
                                modifier = Modifier
                                    .clickable { onModuloClick(it) }
                                    .padding(bottom = 15.dp)
                                    .fillMaxWidth(),
                                elevation = 5.dp
                            ) {
                                Column {
                                    Text(
                                        text = it.nombre,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                    Text(
                                        text = "Creditos: " + it.creditos,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
                                        maxLines = 3
                                    )
                                    Text(
                                        text = "Duración: " + it.duracion,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 10.dp),
                                        maxLines = 3
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