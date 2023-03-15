package com.isaacsufyan.mvi.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.isaacsufyan.mvi.storage.dao.EmployeeDao
import com.isaacsufyan.mvi.storage.entity.AddressEntity
import com.isaacsufyan.mvi.storage.entity.EmployeeEntity
import com.isaacsufyan.mvi.storage.entity.GenderEntity

@Database(
    entities = [GenderEntity::class, AddressEntity::class, EmployeeEntity::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}
