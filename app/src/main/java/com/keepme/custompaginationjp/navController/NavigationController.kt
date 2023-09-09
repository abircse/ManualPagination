package com.keepme.custompaginationjp.navController

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.keepme.custompaginationjp.screens.CustomerDetailsScreen
import com.keepme.custompaginationjp.screens.CustomerScreen

@Composable
fun NavigationComposable(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            CustomerScreen(navController)
        }

        composable("details") {
            CustomerDetailsScreen(navController)
        }
    }
}