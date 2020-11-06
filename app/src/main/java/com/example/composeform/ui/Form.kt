package com.example.composeform.ui

import android.widget.CheckBox
import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient

data class Form (
    val prompts: List<FormElement>? = null,
    val submitLabel: String? = "",
    val path: String? = ""
) : ComposableJson {
    @Composable
    override fun compose() {
        val fields = prompts?.map { remember { mutableStateOf(it.initValue?:"") }}?:listOf()
        Column {
            prompts?.zip(fields)?.map {
                it.first.compose(it.second)
            }
            val context = ContextAmbient.current
            Button(onClick = {
                Toast.makeText(context, fields.joinToString { it.value }, Toast.LENGTH_LONG).show()
            }){
                Text(submitLabel?:"")
            }
        }
    }
}

data class FormElement (
    val prompt: String?,
    val initValue: String? = "",
    val type: String? = "TextField"
) {
    @Composable
    fun compose(state: MutableState<String>) {
        when(type) {
            "TextField" -> TextField(value = state.value, onValueChange = { state.value = it}, label = { Text(prompt?:"")})
            "CheckBox" -> Row {
                Checkbox(checked = state.value.equals("true", true), onCheckedChange = { it -> state.value = it.toString()})
                Text(prompt?:"")
            }
            "Header" -> Text(prompt?:"")
        }
    }
}
