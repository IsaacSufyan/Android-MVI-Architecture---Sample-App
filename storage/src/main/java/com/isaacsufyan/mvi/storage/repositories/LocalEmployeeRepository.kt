package com.isaacsufyan.mvi.storage.repositories

import com.isaacsufyan.mvi.domain.model.Employee
import com.isaacsufyan.mvi.domain.model.Gender
import com.isaacsufyan.mvi.domain.repositories.EmployeeRepository
import com.isaacsufyan.mvi.storage.dao.EmployeeDao

class LocalEmployeeRepository(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {
    override suspend fun getGenders(): List<Gender> {
        return employeeDao.getAllGenders().map { Gender(it.uid, it.name) }
    }

    override suspend fun saveEmployee(employee: Employee) {
        employeeDao.insertDomainEmployee(employee)
    }
}
