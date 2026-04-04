package com.example.aspirebc.ui.payment

import com.example.aspirebc.domain.model.MemberPerformance
import com.example.aspirebc.domain.model.Payment
import com.example.aspirebc.mvi.MviEffect
import com.example.aspirebc.mvi.MviIntent
import com.example.aspirebc.mvi.MviState

class PaymentsContract {
    sealed interface Intent : MviIntent {
        data object LoadPaymentData : Intent
        data class ConfirmPayment(val paymentId: String) : Intent
    }

    data class State(
        val payments: List<Payment> = emptyList(),
        val performance: MemberPerformance? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) : MviState

    sealed interface Effect : MviEffect {
        data class ShowMessage(val message: String) : Effect
    }
}
