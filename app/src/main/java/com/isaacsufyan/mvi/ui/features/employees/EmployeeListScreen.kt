package com.isaacsufyan.mvi.ui.features.employees

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.isaacsufyan.mvi.storage.model.EmployeeWithGenderAndAddresses
import com.isaacsufyan.mvi.ui.composables.BaseComposeScreen
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel

@Composable
fun EmployeeListScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: EmployeeListViewModel = getViewModel()
) {
    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) { state, eventHandler ->
        Scaffold {
            EmployeeListScreenBody(employees = viewModel.employees)
        }
    }
}

@Composable
fun EmployeeListScreenBody(employees: Flow<PagingData<EmployeeWithGenderAndAddresses>>) {
    val lazyEmployeeItems: LazyPagingItems<EmployeeWithGenderAndAddresses> =
        employees.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyEmployeeItems) { employee ->
            Text(text = employee?.employee?.lastName.orEmpty())
        }
    }
}
