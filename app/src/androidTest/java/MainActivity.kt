package com.example.hw_5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw_5.ui.theme.HW_5Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HW_5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Main Activity")
                    MyApp()
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        //text = "Hello $name!",
        text = "$name",
        modifier = modifier
    )
}*/

@Composable
fun MyApp() {

    var showImage by remember { mutableStateOf(false) }
    val imageResourceId = R.drawable.image // Change to your image resource ID

    Column {
        Text(text = "Home Screen")
        Row {
            // Button to open SecondActivity
            LoadImage(showImage){
                showImage = true
            }
            OpenUserSettingsButton()

            }
            // Display image below buttons
            if (showImage) {
                LoadLocalImage(imageResourceId = imageResourceId)
            }
        }
}

@Composable
fun OpenUserSettingsButton() {
    val context = LocalContext.current

    Button(onClick = {
        val intent = Intent(context, UserSettings::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "User Settings")
    }
}

@Composable
fun LoadImage(showImage: Boolean, onClick:() -> Unit) {
    //val context = LocalContext.current

    Button(onClick = onClick){ //}{ showImage = true }) {
        Text(text = "Load Image")
    }

    //Spacer(modifier = Modifier.height(16.dp))


}

@Composable
fun LoadLocalImage(imageResourceId: Int) {
    val painter: Painter = painterResource(id = imageResourceId)

    Image(
        painter = painter,
        contentDescription = "Local Image",
        modifier = Modifier.size(200.dp) // Adjust size as needed
    )
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HW_5Theme {
        Greeting("Android")
    }
}*/
