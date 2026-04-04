package com.example.aspirebc.ui.home

import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class HomeContract {
    sealed interface Intent : MviIntent {
        data object LoadSessions : Intent
        data class JoinSession(val sessionId: String) : Intent
        data object AddSessionClicked : Intent
    }

    data class State(
        val sessions: List<Session> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateToCreateSession : Effect
        data class ShowError(val message: String) : Effect
        data object SessionJoined : Effect
    }
}
