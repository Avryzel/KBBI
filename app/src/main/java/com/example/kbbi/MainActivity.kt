package com.example.kbbi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kbbi.ui.KBBIScreen
import com.example.kbbi.ui.theme.KBBITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KBBITheme {
                KBBIScreen()
            }
        }
    }
}