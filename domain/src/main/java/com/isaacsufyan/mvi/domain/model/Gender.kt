package com.isaacsufyan.mvi.domain.model

data class Gender(
    val id: Long,
    val name: String
) {
    override fun toString() = name
}
