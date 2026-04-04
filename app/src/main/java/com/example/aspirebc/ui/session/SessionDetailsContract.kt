package com.example.aspirebc.ui.session

import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class SessionDetailsContract {
    sealed interface Intent : MviIntent {
        data class LoadSession(val sessionId: String) : Intent
        data object ConfirmSpotClicked : Intent
    }

    data class State(
        val session: Session? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateBack : Effect
        data class ShowMessage(val message: String) : Effect
    }
}
