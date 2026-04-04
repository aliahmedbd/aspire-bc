package com.example.aspirebc.ui.member

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.usecase.GetMembersUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase
) : BaseViewModel<MembersContract.Intent, MembersContract.State, MembersContract.Effect>(
    MembersContract.State()
) {
    init {
        onIntent(MembersContract.Intent.LoadMembers)
    }

    override fun onIntent(intent: MembersContract.Intent) {
        when (intent) {
            MembersContract.Intent.LoadMembers -> loadMembers()
            is MembersContract.Intent.SearchMembers -> {
                updateState { copy(searchQuery = intent.query) }
                applyFilters()
            }
            is MembersContract.Intent.FilterMembers -> {
                updateState { copy(selectedFilter = intent.level) }
                applyFilters()
            }
        }
    }

    private fun loadMembers() {
        viewModelScope.launch {
            getMembersUseCase()
                .onStart { updateState { copy(isLoading = true) } }
                .collect { members ->
                    updateState { 
                        copy(
                            members = members, 
                            filteredMembers = members, 
                            isLoading = false 
                        ) 
                    }
                }
        }
    }

    private fun applyFilters() {
        val query = currentState.searchQuery
        val filter = currentState.selectedFilter
        
        val filtered = currentState.members.filter { member ->
            val matchesQuery = member.name.contains(query, ignoreCase = true)
            val matchesFilter = if (filter == "ALL PLAYERS") true else member.level == filter
            matchesQuery && matchesFilter
        }
        
        updateState { copy(filteredMembers = filtered) }
    }
}
