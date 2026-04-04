package com.example.aspirebc.ui.home

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.repository.SessionRepository
import com.example.aspirebc.domain.usecase.GetSessionsUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val sessionRepository: SessionRepository
) : BaseViewModel<HomeContract.Intent, HomeContract.State, HomeContract.Effect>(
    HomeContract.State()
) {
    init {
        onIntent(HomeContract.Intent.LoadSessions)
    }

    override fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.LoadSessions -> loadSessions()
            is HomeContract.Intent.JoinSession -> joinSession(intent.sessionId)
            HomeContract.Intent.AddSessionClicked -> sendEffect(HomeContract.Effect.NavigateToCreateSession)
        }
    }

    private fun loadSessions() {
        viewModelScope.launch {
            getSessionsUseCase()
                .onStart { updateState { copy(isLoading = true) } }
                .collect { sessions ->
                    updateState { copy(sessions = sessions, isLoading = false) }
                }
        }
    }

    private fun joinSession(sessionId: String) {
        viewModelScope.launch {
            sessionRepository.joinSession(sessionId).collect { result ->
                result.onSuccess {
                    sendEffect(HomeContract.Effect.SessionJoined)
                    loadSessions() // Refresh
                }.onFailure {
                    sendEffect(HomeContract.Effect.ShowError(it.message ?: "Failed to join session"))
                }
            }
        }
    }
}
