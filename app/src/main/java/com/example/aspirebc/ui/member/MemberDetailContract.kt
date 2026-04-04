package com.example.aspirebc.ui.member

import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class MemberDetailContract {
    sealed interface Intent : MviIntent {
        data class LoadMember(val memberId: String) : Intent
        data object ShareClicked : Intent
        data object MessageClicked : Intent
    }

    data class State(
        val member: Member? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateBack : Effect
        data class ShowMessage(val message: String) : Effect
    }
}
