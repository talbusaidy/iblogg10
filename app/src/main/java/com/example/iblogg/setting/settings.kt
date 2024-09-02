package com.example.iblogg.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iblogg.R
import com.example.iblogg.navigation.ROUTE_HOME
import com.example.iblogg.navigation.ROUTE_Notification
import com.example.iblogg.navigation.ROUTE_PROFILE
import com.example.iblogg.navigation.ROUTE_SETTING
import com.example.iblogg.ui.theme.components.HeaderText
import com.example.iblogg.ui.theme.screens.TopBar
import com.google.firebase.annotations.concurrent.Background

@Composable
fun SettingScreen(navController: NavController){
    Scaffold(
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
    ){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Headertext()
            ProfileCardUi()
            GeneralOptionsUi()
            SupportOptionsUi()

        }
    }

}


@Composable
fun SupportOptionsUi() {
    Column (modifier = Modifier.padding(horizontal = 14.dp).padding(top = 10.dp)){  }
Text(
    text = "Support",
    fontFamily = FontFamily.Monospace,
    color = Color.White,
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold,
    modifier = Modifier.padding(vertical = 8.dp)

)
    SupportItem(
        icon = R.drawable.email_24,
        mainText = "Contact",
        onClick = {}
    )
    SupportItem(
        icon = R.drawable.feedback_24,
        mainText = "Feedback",
        onClick = {}
    )
    SupportItem(
        icon = R.drawable.about_24,
        mainText = "About",
        onClick = {}
    )
    SupportItem(
        icon = R.drawable.policy_24,
        mainText = "policy",
        onClick = {}
    )
}

@Composable
fun SupportItem(
    icon: Int,
    mainText: String,
    onClick: () -> Unit
) {
    Card(onClick = onClick,
        modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
    ) {
Row (
    modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
){
    Row (verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(34.dp)){
            Icon(painter = painterResource(id = icon),
                contentDescription = "",
                tint = Color.Green,
                modifier = Modifier.padding(8.dp))
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = mainText,
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        Icon(painter = painterResource(id = R.drawable.next_24),
            contentDescription = "",
            modifier = Modifier.size(34.dp),
            tint = Color.Green)
    }
}
    }
}

@Composable
fun GeneralOptionsUi() {
    Column (
        modifier = Modifier.padding(horizontal = 14.dp).padding(top = 10.dp)
    ){
        Text(text = "General",
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White

        )
        GeneralSettingsItem(
            icon = R.drawable.notifications_24,
            mainText = "Notification",
            subText = "Customize Notifications",
            onClick = { ROUTE_Notification}
        )
        GeneralSettingsItem(
            icon = R.drawable.notifications_24,
            mainText = "More Customizations",
            subText = "Customize it more to fit your usage",
            onClick = { ROUTE_Notification}
        )
    }
}

@Composable
fun GeneralSettingsItem(
    icon: Int,
    mainText: String,
    subText: String,
    onClick:()->Unit
) {
    Card(onClick = onClick,
        modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(0.dp)) {
        Row (
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                ) {
                    Icon(
                        painter = painterResource(id =icon),
                        contentDescription = "", tint = Color.Green,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = mainText,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,

                    )
                    Text(
                        text = subText,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }

            }
            Icon(painter = painterResource(id = R.drawable.next_24),
                contentDescription = "", tint = Color.Green,
                modifier = Modifier.size(34.dp))


        }

    }
}

@Composable
fun ProfileCardUi(){
    Card (
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
    ){
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Check Out Your Profile",
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text("Talibalbusaidy1130@gmail.com",
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light)
                Button(onClick = { ROUTE_PROFILE},
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(
                        text = "View",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = Color.Black

                    )
                }
            }
            Image(painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "",
                modifier = Modifier.height(120.dp).fillMaxWidth())
        }
    }
}



@Composable
fun Headertext(){
    Text("Settings",
fontFamily = FontFamily.Monospace,
        fontSize = 16.sp,
color = Color.Green,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp),
        fontWeight = FontWeight.Bold
        )
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingScreenPrev(){
    SettingScreen(rememberNavController())
}