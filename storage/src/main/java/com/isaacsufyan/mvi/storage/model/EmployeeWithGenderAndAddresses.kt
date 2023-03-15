package com.isaacsufyan.mvi.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.isaacsufyan.mvi.storage.entity.AddressEntity
import com.isaacsufyan.mvi.storage.entity.EmployeeEntity
import com.isaacsufyan.mvi.storage.entity.GenderEntity

data class EmployeeWithGenderAndAddresses(
    @Embedded val employee: EmployeeEntity,

    @Relation(
        parentColumn = "genderId",
        entityColumn = "uid"
    )
    val gender: GenderEntity,

    @Relation(
        parentColumn = "employeeId",
        entityColumn = "employeeId"
    )
    val addresses: List<AddressEntity>
)
