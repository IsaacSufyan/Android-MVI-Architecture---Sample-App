package com.isaacsufyan.mvi.ui.features.add

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.isaacsufyan.mvi.ui.features.add.components.AddEmployeeScreenBody
import com.isaacsufyan.mvi.ui.composables.BaseComposeScreen
import com.isaacsufyan.mvi.utils.successData
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun AddEmployeeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AddEmployeeViewModel = getViewModel()
) {
    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) { state, eventHandler ->
        Scaffold {
            AddEmployeeScreenBody(
                state = state.successData,
                eventHandler = eventHandler
            )
        }
    }
}
