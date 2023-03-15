package com.isaacsufyan.mvi.ui.features.details

import com.isaacsufyan.mvi.utils.BaseViewModel
import com.isaacsufyan.mvi.utils.UiState

class EmployeeDetailsViewModel :
    BaseViewModel<EmployeeDetailsContract.State, EmployeeDetailsContract.Event>(
        initialState = UiState.Loading
    ) {
    override fun handleEvent(event: EmployeeDetailsContract.Event) {
    }
}
