package com.example.aspirebc.ui.member

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.aspirebc.Screen
import com.example.aspirebc.domain.usecase.GetMemberByIdUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberDetailViewModel @Inject constructor(
    private val getMemberByIdUseCase: GetMemberByIdUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MemberDetailContract.Intent, MemberDetailContract.State, MemberDetailContract.Effect>(
    MemberDetailContract.State()
) {
    private val memberId: String = savedStateHandle.toRoute<Screen.MemberDetail>().id

    init {
        onIntent(MemberDetailContract.Intent.LoadMember(memberId))
    }

    override fun onIntent(intent: MemberDetailContract.Intent) {
        when (intent) {
            is MemberDetailContract.Intent.LoadMember -> loadMember(intent.memberId)
            MemberDetailContract.Intent.ShareClicked -> sendEffect(MemberDetailContract.Effect.ShowMessage("Sharing profile..."))
            MemberDetailContract.Intent.MessageClicked -> sendEffect(MemberDetailContract.Effect.ShowMessage("Opening messages..."))
        }
    }

    private fun loadMember(id: String) {
        viewModelScope.launch {
            getMemberByIdUseCase(id)
                .onStart { updateState { copy(isLoading = true) } }
                .collect { member ->
                    updateState { copy(member = member, isLoading = false) }
                }
        }
    }
}
