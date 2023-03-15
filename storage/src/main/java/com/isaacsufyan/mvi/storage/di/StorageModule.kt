package com.isaacsufyan.mvi.storage.di

import androidx.room.Room
import com.isaacsufyan.mvi.domain.DemoDataGenerator
import com.isaacsufyan.mvi.domain.repositories.EmployeeRepository
import com.isaacsufyan.mvi.storage.DatabaseDemoDataGenerator
import com.isaacsufyan.mvi.storage.db.AppDatabase
import com.isaacsufyan.mvi.storage.repositories.LocalEmployeeRepository
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "employee_db"
        ).build()
    }

    single { get<AppDatabase>().employeeDao() }

    single<DemoDataGenerator> {
        DatabaseDemoDataGenerator(
            employeeDao = get()
        )
    }

    single<EmployeeRepository> {
        LocalEmployeeRepository(
            employeeDao = get(),
        )
    }
}
