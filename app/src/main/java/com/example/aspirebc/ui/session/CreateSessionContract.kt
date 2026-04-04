package com.example.aspirebc.ui.session

import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class CreateSessionContract {
    sealed interface Intent : MviIntent {
        data class ChangeCategory(val category: String) : Intent
        data class ChangeDate(val date: String) : Intent
        data class ChangeStartTime(val startTime: String) : Intent
        data class ChangeDuration(val duration: String) : Intent
        data class ChangeLocation(val location: String) : Intent
        data class ChangeCourts(val courts: String) : Intent
        data object SaveClicked : Intent
    }

    data class State(
        val category: String = "weekday",
        val date: String = "05/24/2024",
        val startTime: String = "06:00 PM",
        val duration: String = "2.0",
        val location: String = "",
        val courts: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateBack : Effect
        data class ShowError(val message: String) : Effect
    }
}
