package com.edufelip.catapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.catapp.common.extensions.collectAsStateUI
import com.edufelip.catapp.domain.usecase.GetCatDetailsUseCase
import com.edufelip.catapp.domain.usecase.GetCatsListUseCase
import com.edufelip.catapp.ui.screens.details.DetailsUI
import com.edufelip.catapp.ui.utils.StateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getCatDetailsUseCase: GetCatDetailsUseCase,
    private val getCatsListUseCase: GetCatsListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(DetailsUI())
    val state = _state.asStateFlow()

    fun getCatDetail(id: String) {
        viewModelScope.launch {
            getCatDetailsUseCase.invoke(id).collectAsStateUI { stateUI ->
                when (stateUI) {
                    StateUI.Idle -> Unit
                    is StateUI.Error -> _state.update {
                        it.copy(error = true, loading = false)
                    }
                    StateUI.Loading -> _state.update {
                        it.copy(error = false, loading = true)
                    }
                    is StateUI.Success -> _state.update {
                        it.copy(error = false, loading = false, data = stateUI.data)
                    }
                }
            }
        }
    }
}