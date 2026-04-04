package com.example.aspirebc.ui.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.aspirebc.Screen
import com.example.aspirebc.domain.repository.SessionRepository
import com.example.aspirebc.domain.usecase.GetSessionByIdUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailsViewModel @Inject constructor(
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val sessionRepository: SessionRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SessionDetailsContract.Intent, SessionDetailsContract.State, SessionDetailsContract.Effect>(
    SessionDetailsContract.State()
) {
    // Attempt to get sessionId from route if it was a data class, 
    // but since it's currently a data object, we'll use a mock ID for now or update the route later.
    private val sessionId: String = "1" 

    init {
        onIntent(SessionDetailsContract.Intent.LoadSession(sessionId))
    }

    override fun onIntent(intent: SessionDetailsContract.Intent) {
        when (intent) {
            is SessionDetailsContract.Intent.LoadSession -> loadSession(intent.sessionId)
            SessionDetailsContract.Intent.ConfirmSpotClicked -> confirmSpot()
        }
    }

    private fun loadSession(id: String) {
        viewModelScope.launch {
            getSessionByIdUseCase(id)
                .onStart { updateState { copy(isLoading = true) } }
                .collect { session ->
                    updateState { copy(session = session, isLoading = false) }
                }
        }
    }

    private fun confirmSpot() {
        viewModelScope.launch {
            currentState.session?.let { session ->
                sessionRepository.joinSession(session.id).collect { result ->
                    result.onSuccess {
                        sendEffect(SessionDetailsContract.Effect.ShowMessage("Spot Confirmed!"))
                        loadSession(session.id) // Refresh
                    }.onFailure {
                        sendEffect(SessionDetailsContract.Effect.ShowMessage(it.message ?: "Failed to confirm spot"))
                    }
                }
            }
        }
    }
}
