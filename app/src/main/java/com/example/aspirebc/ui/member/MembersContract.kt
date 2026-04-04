package com.example.aspirebc.ui.member

import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class MembersContract {
    sealed interface Intent : MviIntent {
        data object LoadMembers : Intent
        data class SearchMembers(val query: String) : Intent
        data class FilterMembers(val level: String) : Intent
    }

    data class State(
        val members: List<Member> = emptyList(),
        val filteredMembers: List<Member> = emptyList(),
        val isLoading: Boolean = false,
        val searchQuery: String = "",
        val selectedFilter: String = "ALL PLAYERS",
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data class ShowError(val message: String) : Effect
    }
}
