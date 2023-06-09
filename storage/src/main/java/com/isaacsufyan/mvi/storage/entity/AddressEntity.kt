package com.isaacsufyan.mvi.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    @PrimaryKey val uid: Long? = null,
    val address: String,
    val employeeId: Long?
)
