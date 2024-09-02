package com.example.iblogg.ui.theme.screens

import TopicsViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.iblogg.model.Topics
import com.example.iblogg.navigation.ROUTE_ADD_NEW_TOPIC
import com.example.iblogg.navigation.ROUTE_HOME
import com.example.iblogg.navigation.ROUTE_Notification
import com.example.iblogg.navigation.ROUTE_PROFILE
import com.example.iblogg.navigation.ROUTE_SETTING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homescreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )


    Scaffold(topBar = {
        TopBar(
            navController = navController,
            scrollBehavior = scrollBehavior
        )
    },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(modifier = Modifier
                        .padding(start = 40.dp, end = 45.dp)
                        .size(75.dp), onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home Icon",
                            tint = Color.Green
                        )
                    }


                    IconButton(modifier = Modifier
                        .padding(start = 0.dp, end = 45.dp)
                        .size(75.dp), onClick = {
                        navController.navigate(
                            ROUTE_PROFILE
                        )
                    }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Search Icon",
                            tint = Color.Green
                        )
                    }
                    IconButton(modifier = Modifier
                        .padding(start = 0.dp, end = 0.dp)
                        .size(75.dp), onClick = {
                        navController.navigate(
                            ROUTE_SETTING
                        )
                    }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Notification Icon",
                            tint = Color.Green
                        )
                    }
                }

            )


        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxWidth()
        ) {
          ViewTopics(navController)

        }
    }


}


    @Composable
    fun ViewTopics(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var context = LocalContext.current
            var productRepository = TopicsViewModel(navController, context)


            val emptyUploadState = remember { mutableStateOf(Topics("", "", "", "",)) }
            var emptyUploadsListState = remember { mutableStateListOf<Topics>() }

            var Topic = productRepository.viewTopics(emptyUploadState, emptyUploadsListState)

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "All Clients",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn() {
                    items(Topic) {
                        TopicsItem(
                            imageUrl = "",
                            topic = "",
                            discuss = "",
                            id = "",
                            navController = navController,
                            productRepository = productRepository
                        )
                    }
                }
            }
        }

    }



@Composable
fun TopicsItem(
    imageUrl:String,
    topic:String,
    discuss:String,
    id:String,
    productRepository:TopicsViewModel,
    navController: NavController
){
    var showFullText by remember {
        mutableStateOf(true)
    }
    val context= LocalContext.current
    Column (modifier = Modifier.fillMaxWidth(),){
        Card (
            modifier = Modifier
                .padding(10.dp)
                .height(210.dp)
                .animateContentSize(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray
            )
        ){

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Image(modifier = Modifier
                        .width(200.dp)
                        .height(150.dp)
                        .padding(10.dp),
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop)
                    Row {
                        Button(
                            onClick = {
                                productRepository.deleteTopic(id = "")
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text(text = "DELETE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                        },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text(text = "UPDATE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }
                    }
                }


                Column(modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 15.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )) {
                    Text(text = "Topic",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = topic,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Discuss",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = discuss,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = if (showFullText) 100 else 2,
                        overflow = TextOverflow.Ellipsis)
                }



            }

        }
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomescreenPreview(){
    Homescreen(rememberNavController())
}