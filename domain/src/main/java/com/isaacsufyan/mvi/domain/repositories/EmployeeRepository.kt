package com.isaacsufyan.mvi.domain.repositories

import com.isaacsufyan.mvi.domain.model.Employee
import com.isaacsufyan.mvi.domain.model.Gender

interface EmployeeRepository {
    suspend fun getGenders(): List<Gender>
    suspend fun saveEmployee(employee: Employee)
}
