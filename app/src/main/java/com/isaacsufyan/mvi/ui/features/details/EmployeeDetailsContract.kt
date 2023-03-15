package com.isaacsufyan.mvi.ui.features.details

import com.isaacsufyan.mvi.utils.UiEffect
import com.isaacsufyan.mvi.utils.UiEvent

interface EmployeeDetailsContract {
    sealed class Event : UiEvent

    data class State(
        val name: String = ""
    )

    sealed class Effect : UiEffect
}
