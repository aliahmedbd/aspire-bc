package com.example.aspirebc.mvi

import kotlinx.coroutines.flow.StateFlow

interface MviIntent
interface MviState
interface MviEffect

interface MviViewModel<I : MviIntent, S : MviState, E : MviEffect> {
    val state: StateFlow<S>
    fun onIntent(intent: I)
}
