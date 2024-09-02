package com.example.iblogg.ui.theme.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iblogg.R
import com.example.iblogg.data.AuthViewModel
import com.example.iblogg.navigation.ROUTE_HOME
import com.example.iblogg.navigation.ROUTE_SIGNUP
import com.example.iblogg.ui.theme.IBloggTheme
import com.example.iblogg.ui.theme.components.HeaderText
import com.example.iblogg.ui.theme.components.LoginTextField


val defaultPadding = 16.dp
val itemSpacing = 8.dp




@Composable
fun LoginScreen(navController: NavController
){
    val (userEmail,setEmail) = rememberSaveable {
        mutableStateOf("")}
        val (password,setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (checked,onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        HeaderText(text = "Login",
        modifier = Modifier.padding(vertical = 60.dp)
        )
        LoginTextField(value = userEmail,
            onValueChange = setEmail,
            labelText = "Email",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Start)
            )
        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Start),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()

            )
        Spacer(Modifier.height(itemSpacing))
        Row (){
           Row (
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically
           ){
               Checkbox(checked = checked,
                   onCheckedChange =onCheckedChange )
               Text("Remember me")
               TextButton(onClick = { /*TODO*/ }) {
                   Text(color = Color.Green,
                       text = "Forgot Password?")
               }
           }
        }
        Spacer(Modifier.height(itemSpacing))
        Button(onClick = { val mylogin = AuthViewModel(navController, context)
            mylogin.login(userEmail.trim(), password.trim(),navController.navigate(ROUTE_HOME))},
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF00FF00))
        ) {
            Text(modifier = Modifier,
                text = "Login",
                color = Color.Black)
        }
        Spacer(Modifier.height(320.dp))
        AlternativeLoginOptions(onIconClick = {
            index -> when(index){
                0 -> {
                    Toast.makeText(context,"Login with Desktop",Toast.LENGTH_SHORT).show()
                }
//                1 -> {}
//                2 -> {}

            }
        },
            onSignUpClick = {navController.navigate(ROUTE_SIGNUP)},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomCenter))
    }

}

@Composable
fun AlternativeLoginOptions(
    onIconClick:(index:Int) -> Unit,
    onSignUpClick:() -> Unit,
    modifier: Modifier = Modifier
){
    val iconlist = listOf(
        R.drawable.desktop_login
    )
    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Or Sign in With")
       Row(horizontalArrangement = Arrangement.SpaceEvenly ) {
           iconlist.forEachIndexed { index, iconResId ->
               Icon(painter = painterResource(iconResId), contentDescription = "alternative login",
                   modifier = Modifier
                       .size(32.dp)
                       .clickable {
                           onIconClick(index)
                       }
                       .clip(CircleShape))}}}
               Spacer(Modifier.width(defaultPadding))
               Row (horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically){
                   Text("Don't have an Account?")
                   Spacer(Modifier.height(itemSpacing))
                   TextButton(onClick = onSignUpClick) {
                       Text(color = Color.Green,
                           text = "Sign up")
                   }
               }


       }



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevLoginScreen(){
    IBloggTheme {
        LoginScreen(rememberNavController())
    }
}