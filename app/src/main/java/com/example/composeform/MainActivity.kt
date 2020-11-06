package com.example.composeform

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.composeform.ui.MyApplicationTheme
import com.example.composeform.ui.Screen
import com.example.data.ElementDto
import com.example.data.ScreenDto
import com.example.data.ViewType
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {
    val moshi = Moshi.Builder()
        .build()
    val screenAdapter = moshi.adapter(ScreenDto::class.java)
    val screen = screenAdapter.fromJson("""
        {
            "children" : [
                {
                    "viewtype" : "TEXT",
                    "label" : "Form Header"
                },
                {
                    "viewtype" : "FORM",
                    "children" : [
                        {
                            "viewtype" : "TEXT",
                            "label" : "Personal Information"
                        },
                        {
                            "viewtype" : "TEXTFIELD",
                            "label" : "First"
                        }, {
                            "viewtype" : "TEXTFIELD",
                            "label": "Last"
                        }
                    ],
                    "label" : "Submit"
                }
            ]
        }
    """)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, screen.toString(), Toast.LENGTH_LONG).show()
        setContent {
            MyApplicationTheme {
                MyScreenContent(screen)
            }
        }
    }
}

@Composable
fun MyScreenContent(screen: ScreenDto?) {
    val screenFromConstructor = ScreenDto(
        children = listOf(
            ElementDto(viewtype = ViewType.FORM, children = listOf(
                ElementDto(viewtype=ViewType.TEXTFIELD, label = "First"),
                ElementDto(viewtype=ViewType.TEXTFIELD, label = "Second")
            )
            )
        )
    )
    android.util.Log.e("###", screenFromConstructor.toString())
    android.util.Log.e("###", screen.toString())
    screen?.let {
        Screen(it).compose()
    }  
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyScreenContent(null)
    }
}