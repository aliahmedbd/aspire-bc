package com.example.aspirebc.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.usecase.GetCurrentUserUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel<ProfileContract.Intent, ProfileContract.State, ProfileContract.Effect>(
    ProfileContract.State()
) {
    init {
        onIntent(ProfileContract.Intent.LoadProfile)
    }

    override fun onIntent(intent: ProfileContract.Intent) {
        when (intent) {
            ProfileContract.Intent.LoadProfile -> loadProfile()
            is ProfileContract.Intent.UpdateField -> {
                sendEffect(ProfileContract.Effect.ShowMessage("Updating ${intent.label}..."))
            }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getCurrentUserUseCase()
                .onStart { updateState { copy(isLoading = true) } }
                .collect { user ->
                    // For mock purposes, if repo returns null, we can force a mock user here
                    val finalUser = user ?: com.example.aspirebc.domain.model.User(
                        id = "1",
                        name = "Ali Ahmed",
                        email = "ali@example.com",
                        membershipType = "PREMIUM PLUS"
                    )
                    updateState { copy(user = finalUser, isLoading = false) }
                }
        }
    }
}
