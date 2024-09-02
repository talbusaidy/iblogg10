package com.example.iblogg.Topics

import TopicsViewModel
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.iblogg.R
import com.example.iblogg.navigation.ROUTE_HOME

@Composable
fun AddNewTopic(navController: NavController){
    val imageUri = rememberSaveable() {
        mutableStateOf<Uri?>(null)
    }
    val painter = rememberImagePainter(
        data = imageUri.value ?: R.drawable.baseline_add_24,
        builder = {
            crossfade(true)
        }
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    var topics by remember {
        mutableStateOf(value = "")
    }
    var discuss by remember {
        mutableStateOf(value = "")
    }
    var gender by remember {
        mutableStateOf(value = "")
    }
    var age by remember {
        mutableStateOf(value = "")
    }
    var bio by remember {
        mutableStateOf(value = "")
    }
        Column (
            modifier = Modifier
                .padding()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Text(text = "Post a Topic",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp),
                color = Color.Green)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(200.dp)
                ){
                    Image(painter = painter ,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop)
                }
                Text(text = "")
            }
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Topic Name")},
                placeholder = { Text(text = "Please Enter Topic Name")},
                value = topics,
                onValueChange = {
                        newName -> topics = newName
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Discussion")},
                placeholder = { Text(text = "Please Enter Discussion")},
                value = discuss,
                onValueChange = {
                        newName -> discuss = newName
                })
            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(colors = ButtonDefaults.buttonColors(Color.Green)
                    ,onClick = { navController.navigate(ROUTE_HOME)}) {
                    Text(text = "GO BACK")
                }
                val context = LocalContext.current
                Button(colors = ButtonDefaults.buttonColors(Color.Green)
                    ,onClick = {
                    val TopicsRepository = TopicsViewModel(navController, context)

                    if (imageUri.value != null) {
                        TopicsRepository.saveTopics(
                            filePath = imageUri.value!!,
                            topic = topics,
                            discuss = discuss
                        )
                        navController.navigate(ROUTE_HOME) // Navigate only after saving
                    } else {
                        Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "SAVE")
                }

            }


        }
    }





@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddNewTopicPrev(){
    AddNewTopic(rememberNavController())
}