package com.isaacsufyan.mvi.ui

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.nhaarman.mockitokotlin2.mock
import com.isaacsufyan.mvi.domain.model.Address
import com.isaacsufyan.mvi.domain.model.Gender
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeContract
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeScreen
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeViewModel
import com.isaacsufyan.mvi.utils.ComposeRobot
import com.isaacsufyan.mvi.utils.RobotTest
import com.isaacsufyan.mvi.utils.UiState
import com.isaacsufyan.mvi.utils.assertTextDisplayed
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class AddEmployeeScreenTest : RobotTest<AddEmployeeScreenTestRobot>() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldShowFormOnSuccessState() {
        withRobot {
            startScreen(initialUiState = UiState.Success(AddEmployeeContract.State()))
            loadGenders()
            capture("01_AddEmployeeScreen_Empty_Form")
            fillForm()
            capture("01_AddEmployeeScreen_Filled_Form")
        }
    }

    @Test
    fun shouldShowErrorState() {
        withRobot {
            startScreen(initialUiState = UiState.Failure(Exception()))
            capture("01_AddEmployeeScreen_Failure")
            assertTextDisplayed("Error occurred")
        }
    }

    override fun createRobot() = AddEmployeeScreenTestRobot(composeTestRule)
}

class AddEmployeeScreenTestRobot(
    composeTestRule: ComposeContentTestRule
) :
    ComposeRobot<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeViewModel>(
        AddEmployeeViewModel::class.java,
        composeTestRule
    ) {

    fun startScreen(initialUiState: UiState<AddEmployeeContract.State>) {
        setViewModel(
            AddEmployeeViewModel(
                getGendersUseCase = mock(),
                saveEmployeeUseCase = mock()
            ),
            initialUiState = initialUiState
        )
        setContent {
            AddEmployeeScreen(viewModel = it)
        }
    }

    fun loadGenders() {
        setUiState(
            UiState.Success(
                AddEmployeeContract.State(
                    genders = listOf(
                        Gender(id = 1, name = "Male"),
                        Gender(id = 2, name = "Female")
                    )
                )
            )
        )
    }

    fun fillForm() {
        setUiState(
            UiState.Success(
                AddEmployeeContract.State(
                    genders = listOf(
                        Gender(id = 1, name = "Male"),
                        Gender(id = 2, name = "Female")
                    ),
                    gender = "Male",
                    firstName = "John",
                    lastName = "Doe",
                    addresses = listOf(
                        Address(name = "SomeStreet 1/2"),
                        Address(name = "SecondStreet 4/3"),
                        Address(name = "SuperStreet 11/5"),
                    )
                )
            )
        )
    }
}