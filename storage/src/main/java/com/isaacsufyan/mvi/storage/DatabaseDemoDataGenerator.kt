package com.isaacsufyan.mvi.storage

import com.isaacsufyan.mvi.domain.DemoDataGenerator
import com.isaacsufyan.mvi.storage.dao.EmployeeDao
import com.isaacsufyan.mvi.storage.entity.AddressEntity
import com.isaacsufyan.mvi.storage.entity.EmployeeEntity
import com.isaacsufyan.mvi.storage.entity.GenderEntity

class DatabaseDemoDataGenerator(
    private val employeeDao: EmployeeDao
) : DemoDataGenerator {

    override suspend fun loadDemoDataIntoDB() {
        employeeDao.insertAllGenders(GENDERS)
        employeeDao.insertAllEmployees(EMPLOYEES)
        employeeDao.insertAllAddresses(ADDRESSES)
    }

    companion object {
        val ADDRESSES = listOf(
            AddressEntity(1, "Islamabad", 1),
        )

        val GENDERS = listOf(
            GenderEntity(1, "Man"),
            GenderEntity(2, "Woman"),
        )

        val EMPLOYEES = listOf(
            EmployeeEntity(1, "Sufyan", "Sattar", 100, 1),
        )
    }
}
