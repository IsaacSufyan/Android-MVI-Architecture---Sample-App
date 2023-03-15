package com.isaacsufyan.mvi.domain.usecases

import com.isaacsufyan.mvi.domain.model.Employee
import com.isaacsufyan.mvi.domain.repositories.EmployeeRepository

interface SaveEmployeeUseCase {
    suspend operator fun invoke(employee: Employee)
}

class SaveEmployeeUseCaseImpl(
    private val employeeRepository: EmployeeRepository
) : SaveEmployeeUseCase {
    override suspend fun invoke(employee: Employee) {
        employeeRepository.saveEmployee(employee)
    }
}