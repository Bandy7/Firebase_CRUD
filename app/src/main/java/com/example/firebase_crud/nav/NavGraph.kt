package com.example.firebase_crud.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebase_crud.screens.AddDataScreen
import com.example.firebase_crud.screens.GetDataScreen
import com.example.firebase_crud.screens.MainScreen
import com.example.firebase_crud.util.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
    ){
        composable(
            route = Screens.MainScreen.route
        ){
            MainScreen(navController = navController)
        }
        composable(
            route = Screens.GetDataScreen.route
        ){
            GetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(
            route = Screens.AddDataScreen.route
        ){
            AddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}