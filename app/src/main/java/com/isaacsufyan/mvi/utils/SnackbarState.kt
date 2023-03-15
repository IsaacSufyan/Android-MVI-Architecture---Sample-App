package com.isaacsufyan.mvi.utils

data class SnackbarState(
    val isShown: Boolean = false,
    val message: String = ""
)
