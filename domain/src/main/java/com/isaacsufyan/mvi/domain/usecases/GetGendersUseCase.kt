package com.isaacsufyan.mvi.domain.usecases

import com.isaacsufyan.mvi.domain.model.Gender
import com.isaacsufyan.mvi.domain.repositories.EmployeeRepository

interface GetGendersUseCase {
    suspend operator fun invoke(): List<Gender>
}

class GetGendersUseCaseImpl(
    private val employeeRepository: EmployeeRepository
) : GetGendersUseCase {
    override suspend fun invoke(): List<Gender> {
        return employeeRepository.getGenders()
    }
}
