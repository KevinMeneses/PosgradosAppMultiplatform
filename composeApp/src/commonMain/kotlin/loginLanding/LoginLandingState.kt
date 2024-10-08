package loginLanding

data class LoginLandingState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val loginError: Boolean = false
)
