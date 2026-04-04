package com.example.aspirebc.ui.profile

import com.example.aspirebc.domain.model.User
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class ProfileContract {
    sealed interface Intent : MviIntent {
        data object LoadProfile : Intent
        data class UpdateField(val label: String, val value: String) : Intent
    }

    data class State(
        val user: User? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data class ShowMessage(val message: String) : Effect
    }
}
