package common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import posgradosappmultiplatform.composeapp.generated.resources.Res
import posgradosappmultiplatform.composeapp.generated.resources.fondo_internas
import posgradosappmultiplatform.composeapp.generated.resources.fondo_internas_solo_texto
import posgradosappmultiplatform.composeapp.generated.resources.fondoiniciosesion

@Composable
fun BoxWithUniautonomaBackground(
    background: Background = Background.INTERNAL,
    modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(background.drawable),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )
        content()
    }
}

enum class Background(val drawable: DrawableResource) {
    LANDING(Res.drawable.fondoiniciosesion),
    INTERNAL(Res.drawable.fondo_internas),
    LOGO(Res.drawable.fondo_internas_solo_texto)
}