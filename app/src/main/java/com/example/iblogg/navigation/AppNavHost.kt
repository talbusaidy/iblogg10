package com.example.iblogg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iblogg.Topics.AddNewTopic
import com.example.iblogg.Topics.UpdateTopic
import com.example.iblogg.setting.SettingScreen
import com.example.iblogg.ui.theme.login.LoginScreen
import com.example.iblogg.ui.theme.screens.Homescreen
import com.example.iblogg.ui.theme.screens.Notificationscreen
import com.example.iblogg.ui.theme.screens.Profilescreen
import com.example.iblogg.ui.theme.screens.Searchscreen
import com.example.iblogg.ui.theme.signup.SignupScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SETTING){

    NavHost(navController = navController,
        startDestination = startDestination) {
        composable(ROUTE_SIGNUP){ SignupScreen(navController) }
        composable(ROUTE_LOGIN){ LoginScreen(navController)}
        composable(ROUTE_HOME){ Homescreen(navController)}
        composable(ROUTE_Notification){ Notificationscreen(navController) }
        composable(ROUTE_PROFILE){ Profilescreen(navController) }
        composable(ROUTE_SEARCH){ Searchscreen(navController) }
        composable(ROUTE_SETTING){ SettingScreen(navController)}
        composable(ROUTE_ADD_NEW_TOPIC){ AddNewTopic(navController)}
        composable(ROUTE_UPDATE_TOPIC){ }

    }


}
