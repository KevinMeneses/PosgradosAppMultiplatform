package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BackButton(
    modifier: Modifier,
    onClick: () -> Unit
)