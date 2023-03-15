package com.isaacsufyan.mvi

import com.isaacsufyan.mvi.domain.AppConfiguration

class ExampleAppConfiguration : AppConfiguration {
    override val debug = BuildConfig.DEBUG
}
