package com.isaacsufyan.mvi.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.isaacsufyan.mvi.R
import com.isaacsufyan.mvi.domain.DemoDataGenerator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val demoDataGenerator: DemoDataGenerator by inject()

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            demoDataGenerator.loadDemoDataIntoDB()
        }

        setContent {
            App()
        }
    }
}
