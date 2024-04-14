package com.edufelip.catapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.catapp.common.extensions.collectAsStateUI
import com.edufelip.catapp.domain.usecase.GetCatsListUseCase
import com.edufelip.catapp.ui.screens.home.HomeUI
import com.edufelip.catapp.ui.utils.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatsListUseCase: GetCatsListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeUI())
    val state = _state.asStateFlow()

    fun getCatList() {
        viewModelScope.launch {
            getCatsListUseCase.invoke().collectAsStateUI { stateUI ->
                when (stateUI) {
                    StateUI.Idle -> Unit
                    is StateUI.Error -> _state.update {
                        it.copy(error = true, isLoading = false)
                    }
                    StateUI.Loading -> _state.update {
                        it.copy(error = false, isLoading = true)
                    }
                    is StateUI.Success -> _state.update {
                        it.copy(error = false, isLoading = false, data = stateUI.data)
                    }
                }
            }
        }
    }
}