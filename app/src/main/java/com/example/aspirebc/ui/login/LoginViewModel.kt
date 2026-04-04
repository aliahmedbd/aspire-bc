package com.example.aspirebc.ui.login

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.usecase.LoginUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginContract.Intent, LoginContract.State, LoginContract.Effect>(
    LoginContract.State()
) {
    override fun onIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.SelectMembership -> {
                updateState { copy(selectedMembership = intent.membershipType) }
            }
            LoginContract.Intent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginUseCase(currentState.selectedMembership)
                .onStart { updateState { copy(isLoading = true) } }
                .collect { result ->
                    updateState { copy(isLoading = false) }
                    result.onSuccess {
                        sendEffect(LoginContract.Effect.NavigateToHome)
                    }.onFailure {
                        sendEffect(LoginContract.Effect.ShowError(it.message ?: "Login failed"))
                    }
                }
        }
    }
}
