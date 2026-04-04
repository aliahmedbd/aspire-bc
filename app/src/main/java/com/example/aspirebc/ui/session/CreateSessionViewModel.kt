package com.example.aspirebc.ui.session

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.domain.usecase.CreateSessionUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateSessionViewModel @Inject constructor(
    private val createSessionUseCase: CreateSessionUseCase
) : BaseViewModel<CreateSessionContract.Intent, CreateSessionContract.State, CreateSessionContract.Effect>(
    CreateSessionContract.State()
) {
    override fun onIntent(intent: CreateSessionContract.Intent) {
        when (intent) {
            is CreateSessionContract.Intent.ChangeCategory -> updateState { copy(category = intent.category) }
            is CreateSessionContract.Intent.ChangeDate -> updateState { copy(date = intent.date) }
            is CreateSessionContract.Intent.ChangeStartTime -> updateState { copy(startTime = intent.startTime) }
            is CreateSessionContract.Intent.ChangeDuration -> updateState { copy(duration = intent.duration) }
            is CreateSessionContract.Intent.ChangeLocation -> updateState { copy(location = intent.location) }
            is CreateSessionContract.Intent.ChangeCourts -> updateState { copy(courts = intent.courts) }
            CreateSessionContract.Intent.SaveClicked -> saveSession()
        }
    }

    private fun saveSession() {
        viewModelScope.launch {
            val session = Session(
                id = UUID.randomUUID().toString(),
                title = if (currentState.category == "weekday") "Weekday Smash" else "Weekend Open Play",
                type = if (currentState.category == "weekday") "WEEKDAY SESSION" else "WEEKEND SESSION",
                date = "${currentState.date}, ${currentState.startTime}",
                location = currentState.location,
                spotsLeft = 16,
                totalSpots = 16,
                isWeekday = currentState.category == "weekday"
            )

            createSessionUseCase(session)
                .onStart { updateState { copy(isLoading = true) } }
                .collect { result ->
                    updateState { copy(isLoading = false) }
                    result.onSuccess {
                        sendEffect(CreateSessionContract.Effect.NavigateBack)
                    }.onFailure {
                        sendEffect(CreateSessionContract.Effect.ShowError(it.message ?: "Failed to create session"))
                    }
                }
        }
    }
}
