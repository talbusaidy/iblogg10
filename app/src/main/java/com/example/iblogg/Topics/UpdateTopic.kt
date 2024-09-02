package com.example.iblogg.Topics
import TopicsViewModel
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.iblogg.model.Topics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
@Composable
fun UpdateTopic(navController: NavController, id: String) {
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var existingImageUrl by rememberSaveable { mutableStateOf("") }

    val painter = rememberImagePainter(
        data = imageUri ?: existingImageUrl,
        builder = {
            crossfade(true)
        }
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    var topic by remember { mutableStateOf("") }
    var discuss by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Reference to the current topic in Firebase
    val currentDataRef = FirebaseDatabase.getInstance().getReference("Topics/$id")

    // Load existing topic data
    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val topicData = snapshot.getValue(Topics::class.java)
                topicData?.let {
                    topic = it.topic
                    discuss = it.discuss
                    existingImageUrl = it.imageUrl ?: ""
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home Icon")
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings Icon")
                    }
                    IconButton(onClick = { navController.navigate("email") }) {
                        Icon(Icons.Filled.Email, contentDescription = "Email Icon")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navController.navigate("profile") },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile Icon")
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
                .background(Color.Gray)
        ) {
            Text(
                text = "UPDATE TOPIC",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Green)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.navigate("topics_list") }) {
                    Text(text = "ALL TOPICS")
                }
                Button(onClick = {
                    val topicRepository = TopicsViewModel(navController, context)
                    topicRepository.updateTopic(
                        id = id,
                        topic = topic,
                        discuss = discuss,
                        currentImageUrl = existingImageUrl,
                        filePath = imageUri // Use selected image URI or null if unchanged
                    )
                    Toast.makeText(context, "Topic updated successfully!", Toast.LENGTH_SHORT).show()
                    navController.navigate("home")
                }) {
                    Text(text = "UPDATE")
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(180.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "Topic Image",
                        modifier = Modifier
                            .size(180.dp)
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop
                    )
                }
                Text(text = "Change Picture Here")
            }
            OutlinedTextField(
                modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Topic Name") },
                placeholder = { Text(text = "Please Enter Topic Name") },
                value = topic,
                onValueChange = { newName -> topic = newName }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Discussion") },
                placeholder = { Text(text = "Please Enter Discussion") },
                value = discuss,
                onValueChange = { newDiscuss -> discuss = newDiscuss }
            )
        }
    }
}
