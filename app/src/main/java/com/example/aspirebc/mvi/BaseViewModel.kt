package com.example.aspirebc.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : MviIntent, S : MviState, E : MviEffect>(
    initialState: S
) : ViewModel(), MviViewModel<I, S, E> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect: Flow<E> = _effect.receiveAsFlow()

    protected val currentState: S
        get() = _state.value

    abstract override fun onIntent(intent: I)

    protected fun updateState(reducer: S.() -> S) {
        _state.update { currentState.reducer() }
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
