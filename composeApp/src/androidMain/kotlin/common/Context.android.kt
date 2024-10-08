package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getPlatFormContext(): Any? {
    return LocalContext.current
}