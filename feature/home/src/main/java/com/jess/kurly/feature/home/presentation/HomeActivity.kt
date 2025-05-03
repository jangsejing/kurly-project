package com.jess.kurly.feature.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jess.kurly.feature.home.presentation.screen.HomeScreen
import com.jess.kurly.ui.theme.kurlyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    companion object {

        fun newIntent(
            context: Context,
        ): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            kurlyTheme {
                HomeScreen(
                    onBackPressed = {
                        finish()
                    },
                )
            }
        }
    }
}
