package com.example.aspirebc.ui.admin

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.repository.MemberRepository
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<AdminContract.Intent, AdminContract.State, AdminContract.Effect>(
    AdminContract.State()
) {
    init {
        onIntent(AdminContract.Intent.LoadAdminData)
    }

    override fun onIntent(intent: AdminContract.Intent) {
        when (intent) {
            AdminContract.Intent.LoadAdminData -> loadAdminData()
            is AdminContract.Intent.ApproveMember -> approveMember(intent.memberId)
            is AdminContract.Intent.RejectMember -> rejectMember(intent.memberId)
            AdminContract.Intent.AddSessionClicked -> sendEffect(AdminContract.Effect.NavigateToCreateSession)
        }
    }

    private fun loadAdminData() {
        viewModelScope.launch {
            memberRepository.getMembers()
                .onStart { updateState { copy(isLoading = true) } }
                .collect { members ->
                    updateState {
                        copy(
                            activeMembersCount = members.size,
                            pendingApprovals = members.take(2), // Mocking some pending ones
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun approveMember(memberId: String) {
        viewModelScope.launch {
            // Mock approval
            updateState {
                copy(pendingApprovals = pendingApprovals.filter { it.id != memberId })
            }
            sendEffect(AdminContract.Effect.ShowMessage("Member Approved"))
        }
    }

    private fun rejectMember(memberId: String) {
        viewModelScope.launch {
            // Mock rejection
            updateState {
                copy(pendingApprovals = pendingApprovals.filter { it.id != memberId })
            }
            sendEffect(AdminContract.Effect.ShowMessage("Member Rejected"))
        }
    }
}
