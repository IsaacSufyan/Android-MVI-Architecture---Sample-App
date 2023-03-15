package com.isaacsufyan.mvi.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.isaacsufyan.mvi.domain.model.Employee
import com.isaacsufyan.mvi.storage.entity.AddressEntity
import com.isaacsufyan.mvi.storage.entity.EmployeeEntity
import com.isaacsufyan.mvi.storage.entity.GenderEntity
import com.isaacsufyan.mvi.storage.model.EmployeeWithGenderAndAddresses

@Dao
interface EmployeeDao {

    @Transaction
    @Query("SELECT * FROM employees")
    fun pagingSource(): PagingSource<Int, EmployeeWithGenderAndAddresses>

    suspend fun insertDomainEmployee(emp: Employee) {
        val gender = getGenderByName(emp.gender)[0]
        val employeeId = insertEmployee(
            EmployeeEntity(
                firstName = emp.firstName,
                lastName = emp.lastName,
                age = emp.age,
                genderId = gender.uid
            )
        )
        insertAllAddresses(emp.addresses.map {
            AddressEntity(
                address = it.name,
                employeeId = employeeId
            )
        })
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEmployees(employees: List<EmployeeEntity>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAddresses(addresses: List<AddressEntity>):List<Long>

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)

    @Insert
    suspend fun insertAllGenders(genders: List<GenderEntity>):List<Long>

    @Query("SELECT * FROM genders WHERE genders.name = :genderName")
    suspend fun getGenderByName(genderName: String): List<GenderEntity>

    @Query("SELECT * FROM genders")
    suspend fun getAllGenders(): List<GenderEntity>
}
