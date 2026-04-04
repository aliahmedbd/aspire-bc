package com.example.aspirebc.ui.login

import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class LoginContract {
    sealed interface Intent : MviIntent {
        data class SelectMembership(val membershipType: String) : Intent
        data object LoginClicked : Intent
    }

    data class State(
        val selectedMembership: String = "weekday",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateToHome : Effect
        data class ShowError(val message: String) : Effect
    }
}
