package loginLanding

sealed interface LoginLandingEvent {
    data object SSOLogin : LoginLandingEvent
}