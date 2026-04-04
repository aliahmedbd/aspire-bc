package com.example.aspirebc.domain.model

data class Session(
    val id: String,
    val title: String,
    val type: String,
    val date: String,
    val location: String,
    val spotsLeft: Int,
    val totalSpots: Int,
    val isWeekday: Boolean,
    val attendingPlayers: List<String> = emptyList()
)
