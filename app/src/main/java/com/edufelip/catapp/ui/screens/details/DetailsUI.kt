package com.edufelip.catapp.ui.screens.details

import com.edufelip.catapp.domain.model.Cat

data class DetailsUI (
    val loading: Boolean = false,
    val data: Cat? = null,
    val error: Boolean = false,
)