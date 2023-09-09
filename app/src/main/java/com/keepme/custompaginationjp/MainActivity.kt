package com.keepme.custompaginationjp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.keepme.custompaginationjp.navController.NavigationComposable
import com.keepme.custompaginationjp.ui.theme.CustomPaginationJPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomPaginationJPTheme {
                val navController = rememberNavController()
                NavigationComposable(navController)
            }
        }
    }
}
