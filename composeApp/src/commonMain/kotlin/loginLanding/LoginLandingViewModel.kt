package loginLanding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import authentication.AuthenticationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginLandingViewModel(
    private val authenticationRepository: AuthenticationRepository
): ViewModel() {
    private val _state = MutableStateFlow(LoginLandingState())
    val state = _state.asStateFlow()

    fun processEvent(event: LoginLandingEvent) {
        when(event) {
            LoginLandingEvent.SSOLogin -> authenticate()
        }
    }

    private fun authenticate() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        authenticationRepository.loginWithEmailSSO("kevmeneses@gmail.com")
            .onSuccess {
                _state.update { it.copy(isLoggedIn = true) }
            }.onFailure {
                _state.update { it.copy(loginError = true) }
            }

        delay(100)
        _state.value = LoginLandingState()
    }
}