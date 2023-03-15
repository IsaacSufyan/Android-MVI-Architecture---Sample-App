package com.isaacsufyan.mvi.ui.features.employees

import com.isaacsufyan.mvi.utils.UiEffect
import com.isaacsufyan.mvi.utils.UiEvent

interface EmployeeListContract {

    sealed class Event : UiEvent

    data class State(
        val name: String = ""
    )

    sealed class Effect : UiEffect
}
