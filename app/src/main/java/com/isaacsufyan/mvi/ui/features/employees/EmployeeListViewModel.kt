package com.isaacsufyan.mvi.ui.features.employees

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.isaacsufyan.mvi.storage.dao.EmployeeDao
import com.isaacsufyan.mvi.storage.model.EmployeeWithGenderAndAddresses
import com.isaacsufyan.mvi.utils.BaseViewModel
import com.isaacsufyan.mvi.utils.UiState
import kotlinx.coroutines.flow.Flow

class EmployeeListViewModel(
    private val employeeDao: EmployeeDao
) :
    BaseViewModel<EmployeeListContract.State, EmployeeListContract.Event>(
        initialState = UiState.Success(EmployeeListContract.State())
    ) {

    val employees: Flow<PagingData<EmployeeWithGenderAndAddresses>> = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = EMPLOYEES_PAGE_SIZE,
            initialLoadSize = EMPLOYEES_PAGE_SIZE,
            prefetchDistance = EMPLOYEES_PAGE_SIZE,
        ),
        pagingSourceFactory = { employeeDao.pagingSource() }
    ).flow.cachedIn(viewModelScope)

    override fun handleEvent(event: EmployeeListContract.Event) {}

    fun onAddEmployeeButtonClicked() {
//        navigateTo(EmployeeListFragmentDirections.toAddEmployeeFragment())
    }

    companion object {
        const val EMPLOYEES_PAGE_SIZE = 10
    }
}
