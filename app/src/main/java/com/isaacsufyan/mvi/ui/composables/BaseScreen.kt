package com.isaacsufyan.mvi.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.isaacsufyan.mvi.ui.composables.snackbar.HandleSnackbarIfSupported
import com.isaacsufyan.mvi.utils.BaseViewModel
import com.isaacsufyan.mvi.utils.UiEvent
import com.isaacsufyan.mvi.utils.UiState

@Composable
fun <STATE, EVENT : UiEvent, VM : BaseViewModel<STATE, EVENT>> BaseComposeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: VM,
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter") renderOnLoading: @Composable (eventHandler: (EVENT) -> Unit) -> Unit = {
        Scaffold {
            CenteredCircularProgressIndicator()
        }
    },
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter") renderOnFailure: @Composable (eventHandler: (EVENT) -> Unit) -> Unit = {
        Scaffold {
            Text("Error occurred")
        }
    },
    renderOnSuccess: @Composable (state: UiState<STATE>, eventHandler: (EVENT) -> Unit) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    HandleSnackbarIfSupported(lifecycleOwner, viewModel, scaffoldState)

    val state by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.uiState,
        initialState = viewModel.initialState
    )

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke(viewModel.eventHandler)
        is UiState.Success -> {
            renderOnSuccess.invoke(state, viewModel.eventHandler)
        }
        is UiState.Failure -> renderOnFailure.invoke(viewModel.eventHandler)
        else -> renderOnLoading.invoke(viewModel.eventHandler)
    }
}


