package com.isaacsufyan.mvi.di

import com.isaacsufyan.mvi.ExampleAppConfiguration
import com.isaacsufyan.mvi.domain.AppConfiguration
import com.isaacsufyan.mvi.domain.usecases.GetGendersUseCase
import com.isaacsufyan.mvi.domain.usecases.GetGendersUseCaseImpl
import com.isaacsufyan.mvi.domain.usecases.SaveEmployeeUseCase
import com.isaacsufyan.mvi.domain.usecases.SaveEmployeeUseCaseImpl
import com.isaacsufyan.mvi.ui.features.add.AddEmployeeViewModel
import com.isaacsufyan.mvi.ui.features.details.EmployeeDetailsViewModel
import com.isaacsufyan.mvi.ui.features.employees.EmployeeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        ExampleAppConfiguration()
    }

    factory<GetGendersUseCase> {
        GetGendersUseCaseImpl(
            employeeRepository = get()
        )
    }

    factory<SaveEmployeeUseCase> {
        SaveEmployeeUseCaseImpl(
            employeeRepository = get()
        )
    }

    viewModel {
        EmployeeListViewModel(
            employeeDao = get()
        )
    }

    viewModel {
        AddEmployeeViewModel(
            getGendersUseCase = get(),
            saveEmployeeUseCase = get()
        )
    }

    viewModel {
        EmployeeDetailsViewModel()
    }
}
