package com.example.composeform

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.composeform.ui.MyApplicationTheme
import com.example.composeform.ui.Screen
import com.example.data.ElementDto
import com.example.data.ScreenDto
import com.example.data.ViewType
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceLocator.put(BackEndService::class.java, FakeBackEndService())
        setContent {
            MyApplicationTheme {
                MyScreenContent()
            }
        }
    }
}

data class StringHolder(var held: MutableState<String>)
val ScreenJson = ambientOf<StringHolder>()

@Composable
fun MyScreenContent() {
    val screenJson = ServiceLocator.resolve(BackEndService::class.java).getPage("/", mapOf())
    val screenJsonString = StringHolder(remember {mutableStateOf(screenJson)})
    val moshi = Moshi.Builder()
        .build()
    val screenAdapter = moshi.adapter(ScreenDto::class.java)
    Providers(ScreenJson provides screenJsonString) {
        val holder = ScreenJson.current
        screenAdapter.fromJson(holder.held.value)?.let {
            Screen(it).compose()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyScreenContent()
    }
}