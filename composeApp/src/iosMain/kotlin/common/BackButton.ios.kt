package common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun BackButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            modifier = Modifier
                .size(30.dp)
                .clickable(onClick = onClick),
            contentDescription = "",
            tint = Color.White
        )
    }
}