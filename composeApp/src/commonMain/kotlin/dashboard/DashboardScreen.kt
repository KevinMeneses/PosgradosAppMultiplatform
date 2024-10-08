package dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
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
import common.LoadingScreen
import common.colorGold
import common.colorGoldDark
import common.titleAlignment
import docentes.Docente
import modulos.Modulo
import org.jetbrains.compose.resources.painterResource
import posgradosappmultiplatform.composeapp.generated.resources.Res
import posgradosappmultiplatform.composeapp.generated.resources.alerta
import posgradosappmultiplatform.composeapp.generated.resources.icono_clase
import posgradosappmultiplatform.composeapp.generated.resources.icono_horario
import posgradosappmultiplatform.composeapp.generated.resources.icono_persona

object DashboardScreen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun show(
        state: DashboardState = DashboardState(),
        onModuloClick: (Modulo) -> Unit,
        onDocenteClick: (Docente) -> Unit,
        onBackClick: () -> Unit,
        onEvent: (DashboardEvent) -> Unit
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
                Text(
                    text = state.posgrado?.nombre.orEmpty(),
                    fontSize = 20.sp,
                    color = colorGold,
                    textAlign = titleAlignment(),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                )
                Text(
                    text = "Semestre actual: ${state.currentSemestre}",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                LazyColumn {
                    stickyHeader {
                        TabRow(
                            selectedTabIndex = state.selectedSemestre - 1,
                            backgroundColor = colorGold,
                            indicator = {
                                TabRowDefaults.Indicator(
                                    modifier = Modifier.tabIndicatorOffset(it[state.selectedSemestre-1]),
                                    color = colorGoldDark
                                )
                            },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            repeat(state.totalSemestres) {
                                val semestre = it + 1
                                Tab(
                                    selected = semestre == state.selectedSemestre,
                                    onClick = {
                                        DashboardEvent
                                            .SemesterSelected(semestre)
                                            .run(onEvent)
                                    }
                                ) {
                                    Text(
                                        text = "Semestre $semestre",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(15.dp)
                                    )
                                }
                            }
                        }
                    }
                    items(state.modulos.size) { position ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .padding(bottom = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val modulo = state.modulos.getOrNull(position)
                                val docente = state.docentes.getOrNull(position)
                                val nombreDocente = docente?.nombre + " " + docente?.apellido

                                val onModuloClicked = fun () { onModuloClick(modulo!!) }
                                val onDocenteClicked = fun () { onDocenteClick(docente!!) }

                                Column(
                                    modifier = Modifier.weight(0.8f)
                                ) {
                                    Text(
                                        text = modulo?.nombre.orEmpty(),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.clickable(onClick = onModuloClicked)
                                    )
                                    Text(
                                        text = modulo?.duracion.orEmpty(),
                                        fontSize = 15.sp,
                                        modifier = Modifier.clickable(onClick = onModuloClicked)
                                    )
                                    Text(
                                        text = nombreDocente,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(bottom = 15.dp)
                                            .clickable(onClick = onDocenteClicked),
                                        fontWeight = FontWeight.Medium,
                                        color = colorGold
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.icono_persona),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable(onClick = onDocenteClicked)
                                        )
                                        Image(
                                            painter = painterResource(Res.drawable.icono_clase),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable(onClick = onModuloClicked)
                                        )
                                        Image(
                                            painter = painterResource(Res.drawable.icono_horario),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp).clickable {  }
                                        )
                                        Image(
                                            painter = painterResource(Res.drawable.alerta),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp).clickable {  }
                                        )
                                    }
                                }
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
                                        .weight(0.2f)
                                        .height(100.dp)
                                        .clickable(onClick = onDocenteClicked)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}