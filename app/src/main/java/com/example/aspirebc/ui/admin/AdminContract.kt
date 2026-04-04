package com.example.aspirebc.ui.admin

import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class AdminContract {
    sealed interface Intent : MviIntent {
        data object LoadAdminData : Intent
        data class ApproveMember(val memberId: String) : Intent
        data class RejectMember(val memberId: String) : Intent
        data object AddSessionClicked : Intent
    }

    data class State(
        val activeMembersCount: Int = 0,
        val pendingApprovals: List<Member> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data object NavigateToCreateSession : Effect
        data class ShowMessage(val message: String) : Effect
    }
}
