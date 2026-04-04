package com.example.aspirebc.ui.payment

import androidx.lifecycle.viewModelScope
import com.example.aspirebc.domain.repository.PaymentRepository
import com.example.aspirebc.domain.usecase.GetPaymentsUseCase
import com.example.aspirebc.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val getPaymentsUseCase: GetPaymentsUseCase,
    private val paymentRepository: PaymentRepository
) : BaseViewModel<PaymentsContract.Intent, PaymentsContract.State, PaymentsContract.Effect>(
    PaymentsContract.State()
) {
    init {
        onIntent(PaymentsContract.Intent.LoadPaymentData)
    }

    override fun onIntent(intent: PaymentsContract.Intent) {
        when (intent) {
            PaymentsContract.Intent.LoadPaymentData -> loadPaymentData()
            is PaymentsContract.Intent.ConfirmPayment -> confirmPayment(intent.paymentId)
        }
    }

    private fun loadPaymentData() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            // In a real app we'd zip or combine these flows
            paymentRepository.getMemberPerformance().collect { perf ->
                updateState { copy(performance = perf) }
            }
            getPaymentsUseCase().collect { payments ->
                updateState { copy(payments = payments, isLoading = false) }
            }
        }
    }

    private fun confirmPayment(paymentId: String) {
        viewModelScope.launch {
            paymentRepository.confirmPayment(paymentId).collect { result ->
                result.onSuccess {
                    sendEffect(PaymentsContract.Effect.ShowMessage("Payment Confirmed"))
                    loadPaymentData()
                }.onFailure {
                    sendEffect(PaymentsContract.Effect.ShowMessage(it.message ?: "Payment Failed"))
                }
            }
        }
    }
}
