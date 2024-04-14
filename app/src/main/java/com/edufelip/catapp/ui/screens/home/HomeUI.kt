package com.edufelip.catapp.ui.screens.home

import com.edufelip.catapp.domain.model.Cat

data class HomeUI (
    val isLoading: Boolean = false,
    val data: List<Cat> = emptyList(),
    val error: Boolean = false,
)