package com.example.iblogg.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iblogg.navigation.ROUTE_ADD_NEW_TOPIC
import com.example.iblogg.navigation.ROUTE_HOME
import com.example.iblogg.navigation.ROUTE_Notification
import com.example.iblogg.navigation.ROUTE_PROFILE
import com.example.iblogg.navigation.ROUTE_SEARCH
import com.example.iblogg.navigation.ROUTE_SETTING
import com.example.iblogg.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchscreen(navController: NavController){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold (topBar = {
        TopBar(navController = navController,scrollBehavior = scrollBehavior)
    },
        bottomBar = {
            BottomAppBar (
                actions = {
                    IconButton(modifier = Modifier.padding(start = 40.dp, end = 45.dp).size(75.dp),onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription ="Home Icon" ,
                            tint = Color.Green)
                    }


                    IconButton(modifier = Modifier.padding(start = 0.dp, end = 45.dp).size(75.dp),onClick = {navController.navigate(
                        ROUTE_PROFILE)}) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Search Icon",
                            tint = Color.Green)
                    }
                    IconButton(modifier = Modifier.padding(start = 0.dp, end = 0.dp).size(75.dp),onClick = {navController.navigate(
                        ROUTE_SETTING)}) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Notification Icon",
                            tint = Color.Green)
                    }
                }

            )




        }
    ){innerPadding -> Column( modifier = Modifier
        .padding(innerPadding)
        .verticalScroll(rememberScrollState())
        .padding(10.dp)
        .fillMaxWidth()) {

    }}
    @Composable
    fun Screen(modifier: Modifier = Modifier){

    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(modifier: Modifier = Modifier,navController: NavController,
               scrollBehavior: TopAppBarScrollBehavior){
        TopAppBar( modifier = modifier,
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background.copy(0.6f)
            ),
            title = {
                Text(text = "iBlogg",
                    color = Color.Green,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
            },

            actions = {
                IconButton(onClick = { navController.navigate(ROUTE_ADD_NEW_TOPIC)}) {
                    Icon(imageVector = Icons.Rounded.AddCircle,
                        contentDescription = "Add Topic",
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(25.dp),
                        tint = Color.Green)}
                IconButton(onClick = { navController.navigate(ROUTE_Notification) }) {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 4.dp, end = 16.dp)
                            .size(25.dp),
                        tint = Color.Green
                    )
                }})
    }}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchscreenPrev(){
    Searchscreen(rememberNavController())
}