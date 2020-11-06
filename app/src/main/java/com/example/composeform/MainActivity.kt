package com.example.composeform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.composeform.ui.Form
import com.example.composeform.ui.FormElement
import com.example.composeform.ui.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyScreenContent() {
    val form = Form(
        prompts = listOf(
            FormElement(prompt = "Personal Information"),
            FormElement(prompt = "First"),
            FormElement(prompt = "Last"),
            FormElement(prompt = "Title"),
            FormElement(prompt = "Happy?", type="CheckBox")
        ),
        submitLabel = "Done"
    )
    form.compose()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyScreenContent()
    }
}