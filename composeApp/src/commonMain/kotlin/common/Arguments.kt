package common

import androidx.navigation.NavType
import androidx.navigation.navArgument

fun stringArgs(vararg args: String) = args.map {
    navArgument(
        name = it,
        builder = { type = NavType.StringType }
    )
}