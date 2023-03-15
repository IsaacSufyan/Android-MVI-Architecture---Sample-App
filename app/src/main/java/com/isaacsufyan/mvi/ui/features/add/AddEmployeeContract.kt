package com.isaacsufyan.mvi.ui.features.add

import com.isaacsufyan.mvi.domain.model.Address
import com.isaacsufyan.mvi.domain.model.Gender
import com.isaacsufyan.mvi.utils.UiEffect
import com.isaacsufyan.mvi.utils.UiEvent

class AddEmployeeContract {
    sealed class Event : UiEvent {
        object AddAddressEvent : Event()
        class RemoveAddressEvent(val address: Address) : Event()
        class UpdateFormEvent(
            val firstName: String? = null,
            val lastName: String? = null,
            val gender: String? = null,
            val address: String? = null,
        ) : Event()

        object SaveEmployeeEvent : Event()
    }

    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val age: Int = 0,
        val gender: String = "",
        val address: String = "",
        val addresses: List<Address> = emptyList(),
        val genders: List<Gender> = emptyList(),
    )

    sealed class Effect : UiEffect
}
