package com.example.hw_5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw_5.ui.theme.HW_5Theme

val radioOptions = listOf("Java", "Kotlin", "Swift")

class UserSettings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedOption = getSelectedOption()
        setContent {
            HW_5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp2(selectedOption, this@UserSettings::saveSelectedOption)
                }
            }
        }
    }

    private fun getSelectedOption(): String {
        val sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE)
        return sharedPreferences.getString("selected_option", radioOptions[0]) ?: radioOptions[0]
    }

    fun saveSelectedOption(selectedOption: String) {
        val sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("selected_option", selectedOption)
            apply()
        }
    }
}

@Composable
fun MyApp2(selectedOptionInit: String, saveOption: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf(selectedOptionInit) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // Ensures the Column uses full width available
        horizontalAlignment = Alignment.CenterHorizontally // Centers all children horizontally
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            selectedOption = text
                            saveOption(selectedOption)
                        }
                    )
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null  // onClick is handled by selectable, so we pass null here
                )
                Text(text = text, modifier = Modifier.padding(start = 16.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        ) {
            Text("Back")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HW_5Theme {
        MyApp2(radioOptions[0], {})  // Initialize with the first option for preview purposes, using an empty lambda for saving
    }
}
