package com.isaacsufyan.mvi.ui.features.add

import com.isaacsufyan.mvi.domain.model.Address
import com.isaacsufyan.mvi.domain.model.Employee
import com.isaacsufyan.mvi.domain.usecases.GetGendersUseCase
import com.isaacsufyan.mvi.domain.usecases.SaveEmployeeUseCase
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeContract.Event.*
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeContract.State
import com.isaacsufyan.mvi.utils.BaseViewModel
import com.isaacsufyan.mvi.utils.UiState
import com.isaacsufyan.mvi.utils.delegates.CanDisplaySnackbar
import com.isaacsufyan.mvi.utils.delegates.CanDisplaySnackbarImpl
import com.isaacsufyan.mvi.utils.successData

class AddEmployeeViewModel(
    private val getGendersUseCase: GetGendersUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase,
    private val canDisplaySnackbar: CanDisplaySnackbar = CanDisplaySnackbarImpl()
) :
    BaseViewModel<State, AddEmployeeContract.Event>(
        initialState = UiState.Loading
    ), CanDisplaySnackbar by canDisplaySnackbar {

    init {
        loadGenders()
    }

    override fun handleEvent(event: AddEmployeeContract.Event) {
        when (event) {
            is UpdateFormEvent -> handleUpdateFormEvent(event)
            is AddAddressEvent -> handleAddAddressEvent()
            is RemoveAddressEvent -> handleRemoveAddressEvent(event)
            is SaveEmployeeEvent -> handleSaveEmployeeEvent()
        }
    }

    private fun handleUpdateFormEvent(event: UpdateFormEvent) {
        updateUiSuccessState {
            it.copy(
                firstName = event.firstName ?: it.firstName,
                lastName = event.lastName ?: it.lastName,
                gender = event.gender ?: it.gender,
                address = event.address ?: it.address
            )
        }
    }

    private fun handleAddAddressEvent() {
        updateUiSuccessState {
            it.copy(
                address = "",
                addresses = it.addresses.plus(Address(it.address))
            )
        }
    }

    private fun handleRemoveAddressEvent(event: RemoveAddressEvent) {
        val addresses = currentState.successData.addresses.toMutableList()
        addresses.remove(event.address)
        updateUiSuccessState {
            it.copy(
                addresses = addresses
            )
        }
    }

    private fun handleSaveEmployeeEvent() {
        currentState.successData.run {
            val isValid =
                firstName.isNotEmpty() && lastName.isNotEmpty() && gender.isNotEmpty() && addresses.isNotEmpty()
            if (isValid) {
                safeLaunch {
                    saveEmployeeUseCase(
                        Employee(
                            firstName = firstName,
                            lastName = lastName,
                            age = age,
                            gender = gender,
                            addresses = addresses
                        )
                    )
                    showSnackbar("New employee saved! :)")
                    clearForm()
                }
            } else {
                showSnackbar("Form is not valid")
            }
        }
    }

    private fun loadGenders() {
        safeLaunch {
            val genders = getGendersUseCase()
            updateUiState { UiState.Success(State(genders = genders)) }
        }
    }

    private fun clearForm() {
        updateUiSuccessState {
            it.copy(
                firstName = "",
                lastName = "",
                gender = "",
                address = "",
                addresses = emptyList()
            )
        }
    }
}
